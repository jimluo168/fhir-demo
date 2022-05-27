package com.ha.fhir.util;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IBaseResource;

public class FhirClient4R4Util {

    public static <T extends IBaseBundle> T getResourceById(String serverBase, String id, Class<T> forResourceCls, Class<T> returnBundleCls) {
        FhirContext ctx = FhirContext.forR4();
        StringClientParam idParam = new StringClientParam("_id");
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        T results = client.search().forResource(forResourceCls).where(idParam.matches().value(id)).returnBundle(returnBundleCls).execute();
        return results;
    }


    public static MethodOutcome createResource(String serverBase, IBaseResource theResource) {
        FhirContext ctx = FhirContext.forR4();
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        return client.create().resource(theResource).prettyPrint().encodedJson().execute();
    }

}
