package com.ha.fhir;

import ca.uhn.fhir.i18n.Msg;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.AuthenticationException;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.IdType;

import java.util.List;

public class HAAuthorizationInterceptor extends AuthorizationInterceptor {

    @Override
    public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {

        // Process authorization header - The following is a fake
        // implementation. Obviously we'd want something more real
        // for a production scenario.
        //
        // In this basic example we have two hardcoded bearer tokens,
        // one which is for a user that has access to one patient, and
        // another that has full access.
        IdType userIdPatientId = null;
        boolean userIsAdmin = false;
        String authHeader = theRequestDetails.getHeader("Authorization");
        if ("Bearer token_user1".equals(authHeader)) {
            // This user has access only to Patient/1 resources
            userIdPatientId = new IdType("Patient", 1L);
        } else if ("Bearer token_admin1".equals(authHeader)) {
            // This user has access to everything
            userIsAdmin = true;
        } else {
            if (authHeader == null &&
                    (StringUtils.equals(theRequestDetails.getRequestPath(), "swagger-ui/")
                    || StringUtils.equals(theRequestDetails.getRequestPath(), "api-docs"))) {
                return new RuleBuilder().allowAll().build();
            }
            // Throw an HTTP 401
            throw new AuthenticationException(Msg.code(644) + "Missing or invalid Authorization header value");
        }

        // If the user is a specific patient, we create the following rule chain:
        // Allow the user to read anything in their own patient compartment
        // Allow the user to write anything in their own patient compartment
        // If a client request doesn't pass either of the above, deny it
        if (userIdPatientId != null) {
            return new RuleBuilder()
                    .allow()
                    .read()
                    .allResources()
                    .inCompartment("Patient", userIdPatientId)

                    .andThen()
                    .allow()
                    .write()
                    .allResources()
                    .inCompartment("Patient", userIdPatientId)

                    .andThen()
                    .allow()
                    .read()
                    .allResources()
                    .inCompartment("Bundle", userIdPatientId)

                    .andThen()
                    .deny()
                    .write()
                    .allResources()
                    .inCompartment("Bundle", userIdPatientId)

                    .andThen()
                    .denyAll()
                    .build();
        }

        // If the user is an admin, allow everything
        if (userIsAdmin) {
            return new RuleBuilder().allowAll().build();
        }

        // By default, deny everything. This should never get hit, but it's
        // good to be defensive
        return new RuleBuilder().denyAll().build();
    }
}