package com.ha.fhir.bundle;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.Bundle;
import org.junit.jupiter.api.Test;

public class RestfulServerUtilsTest {

    @Test
    public void getContextForVersion() {
        Bundle bundle = new Bundle();
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);
        parser.setServerBaseUrl("http://hapi-fhir.apjcorp.com/fhir");
        String resourceStr = parser.encodeResourceToString(bundle);
        System.out.println(resourceStr);
    }
}
