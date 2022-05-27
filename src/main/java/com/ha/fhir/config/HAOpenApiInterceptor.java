package com.ha.fhir.config;

import ca.uhn.fhir.rest.openapi.OpenApiInterceptor;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Collections;

public class HAOpenApiInterceptor extends OpenApiInterceptor {

    @Override
    protected OpenAPI generateOpenApi(ServletRequestDetails theRequestDetails) {
        OpenAPI openApi = super.generateOpenApi(theRequestDetails);
        // Add Authentication to OAS spec.
        openApi.getComponents().addSecuritySchemes("oauth2schema", oauth2ImplicitSecurityScheme());
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("oauth2schema");
        openApi.security(Collections.singletonList(securityRequirement));
        return openApi;
    }

    private SecurityScheme oauth2ImplicitSecurityScheme() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.bearerFormat("JWT");
        securityScheme.scheme("bearer");
        securityScheme.in(SecurityScheme.In.HEADER);
        securityScheme.setName("Authorization");
        OAuthFlows flows = new OAuthFlows();
        securityScheme.flows(flows);
        return securityScheme;
    }
}
