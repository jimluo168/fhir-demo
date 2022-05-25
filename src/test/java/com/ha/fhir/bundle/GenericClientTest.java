package com.ha.fhir.bundle;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Test;

public class GenericClientTest {

    @Test
    public void testSearch() {
        FhirContext ctx = FhirContext.forR4();
        String serverBase = "http://hapi-fhir.apjcorp.com/fhir";
        StringClientParam idParam = new StringClientParam("_id");
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        Bundle results = client.search().forResource(Patient.class)
                .where(idParam.matches().value("16"))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(results);
    }

    @Test
    public void testSearch2() {
        FhirContext ctx = FhirContext.forR4();
        String serverBase = "http://hapi-fhir.apjcorp.com/fhir";
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        Bundle results = client.search().forResource(Patient.class)
                .where(Patient.FAMILY.matches().value("Chalmers"))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(results);
    }

    @Test
    public void testPost() {
        Patient patient = new Patient();
        // ..populate the patient object..
        patient.addIdentifier().setSystem("urn:system").setValue("12345");
        patient.addName().setFamily("Smith").addGiven("John");

        FhirContext ctx = FhirContext.forR4();
        String serverBase = "http://hapi-fhir.apjcorp.com/fhir";
        IGenericClient client = ctx.newRestfulGenericClient(serverBase);
        MethodOutcome outcome = client.create().resource(patient).prettyPrint().encodedJson().execute();

        System.out.println(outcome);
    }


}
