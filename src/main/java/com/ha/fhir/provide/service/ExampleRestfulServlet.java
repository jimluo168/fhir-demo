package com.ha.fhir.provide.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import com.ha.fhir.provide.controller.RestfulPatientResourceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns= {"/test/*"}, displayName="FHIR Server")
public class ExampleRestfulServlet extends RestfulServer {

    private static final long serialVersionUID = 1L;

    @Override
    protected void initialize() throws ServletException {

        // Create a context for the appropriate version
//        setFhirContext(FhirContext.forR4());
//
//        // Register resource providers
//        registerProvider(new RestfulPatientResourceProvider());
//
//        // Format the responses in nice HTML
//        registerInterceptor(new ResponseHighlighterInterceptor());

        /*
         * The servlet defines any number of resource providers, and
         * configures itself to use them by calling
         * setResourceProviders()
         */
        List<IResourceProvider> resourceProviders = new ArrayList<IResourceProvider>();
        resourceProviders.add(new RestfulPatientResourceProvider());
        setResourceProviders(resourceProviders);
    }
}
