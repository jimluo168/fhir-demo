package com.ha.fhir.patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientResourceTest {

    @Test
    public void testXml2json() {
        FhirContext myFhirContext = FhirContext.forR4();

        IParser parser = myFhirContext.newXmlParser();

        Patient patient = parser.parseResource(Patient.class, getClass().getClassLoader().getResourceAsStream("ha-data/xml" + "/Patient" + "-example.xml"));

        Assertions.assertNotNull(patient);

//        IParser jsonP
    }
}
