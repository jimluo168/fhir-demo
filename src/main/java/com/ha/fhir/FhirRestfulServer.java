package com.ha.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.openapi.OpenApiInterceptor;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import ca.uhn.fhir.rest.server.provider.ResourceProviderFactory;
import com.ha.fhir.bundle.controller.BundleResourceProvider;
import com.ha.fhir.organization.controller.OrganizationResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import javax.servlet.ServletException;

@Import(AppProperties.class)
public class FhirRestfulServer extends RestfulServer {
    @Autowired
    private AppProperties appProperties;
//    @Autowired
//    private ResourceProviderFactory resourceProviderFactory;

    @Override
    protected void initialize() throws ServletException {
        super.initialize();
        setFhirContext(FhirContext.forR4());

//        registerProviders(resourceProviderFactory.createProviders());
        registerProvider(new OrganizationResourceProvider());
        registerProvider(new BundleResourceProvider());
        /*
         * Use nice coloured HTML when a browser is used to request the content
         */
        registerInterceptor(new ResponseHighlighterInterceptor());

        registerInterceptor(new OpenApiInterceptor());

        // default to JSON and pretty printing.
        setDefaultPrettyPrint(true);

        setDefaultResponseEncoding(EncodingEnum.JSON);
    }
}
