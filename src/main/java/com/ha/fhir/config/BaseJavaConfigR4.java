package com.ha.fhir.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.provider.ResourceProviderFactory;
import com.ha.fhir.bundle.controller.BundleResourceProvider;
import com.ha.fhir.provide.controller.RestfulPatientResourceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.function.Supplier;

@Configuration
public class BaseJavaConfigR4 extends BaseR4Config {
    @Bean(name = "myResourceProvidersR4")
    public ResourceProviderFactory resourceProvidersR4() {
        ResourceProviderFactory factory = new ResourceProviderFactory();
        factory.addSupplier(() -> rpBundleR4());
        factory.addSupplier(() -> rpPatientR4());

        return factory;
    }

    @Bean(name = "myBundleRpR4")
    @Lazy
    public BundleResourceProvider rpBundleR4() {
        BundleResourceProvider provider = new BundleResourceProvider();
        provider.setContext(fhirContextR4());
        return provider;
    }
    @Bean(name = "rpPatientR4")
    @Lazy
    public RestfulPatientResourceProvider rpPatientR4() {
        RestfulPatientResourceProvider provider = new RestfulPatientResourceProvider();
        provider.setContext(fhirContextR4());
        return provider;
    }

}
