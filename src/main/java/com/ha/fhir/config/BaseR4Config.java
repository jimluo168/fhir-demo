package com.ha.fhir.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.ParserOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//@Configuration
public class BaseR4Config {
    public static FhirContext ourFhirContext = FhirContext.forR4();

    @Bean
    @Primary
    public FhirContext fhirContextR4() {
        FhirContext retVal = ourFhirContext;

        // Don't strip versions in some places
        ParserOptions parserOptions = retVal.getParserOptions();
        parserOptions.setDontStripVersionsFromReferencesAtPaths("AuditEvent.entity.what");

        return retVal;
    }

}
