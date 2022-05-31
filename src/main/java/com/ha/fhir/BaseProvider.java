package com.ha.fhir;

import ca.uhn.fhir.context.FhirContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseProvider {
    private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(BaseProvider.class);
    @Autowired
    private FhirContext myContext;

    public void setContext(FhirContext theContext) {
        myContext = theContext;
    }
}
