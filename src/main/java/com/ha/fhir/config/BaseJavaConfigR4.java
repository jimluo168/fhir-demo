package com.ha.fhir.config;

import ca.uhn.fhir.rest.server.provider.ResourceProviderFactory;
import com.ha.fhir.api.bundle.provider.IBundleResourceProvider;
import com.ha.fhir.impl.bundle.provider.BundleResourceProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BaseJavaConfigR4 extends BaseR4Config {
    @Bean(name = "myResourceProvidersR4")
    public ResourceProviderFactory resourceProvidersR4() {
        ResourceProviderFactory factory = new ResourceProviderFactory();
        factory.addSupplier(() -> rpBundleR4());

        return factory;
    }

    @Bean(name = "myBundleRpR4")
    @Lazy
    public BundleResourceProviderImpl rpBundleR4() {
        BundleResourceProviderImpl provider = new BundleResourceProviderImpl();
        provider.setContext(fhirContextR4());
        return provider;
    }
}
