package com.ha.fhir.util;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseResource;

public class ParserResourceUtil {
    public static String encodeResourceToString(IBaseResource theResource) throws DataFormatException {
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);
        String resourceStr = parser.encodeResourceToString(theResource);
        return resourceStr;
    }

    public static <T extends IBaseResource> T parseResource(Class<T> theResourceType, String theString) throws DataFormatException {
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newJsonParser();
        parser.setPrettyPrint(true);
        T resource = parser.parseResource(theResourceType, theString);
        return resource;
    }

}
