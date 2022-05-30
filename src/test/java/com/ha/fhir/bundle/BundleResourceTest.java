package com.ha.fhir.bundle;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleBuilder;
import com.ha.fhir.util.ParserResourceUtil;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.r4.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class BundleResourceTest {

    @Test
    public void buildBundleResource() {
        FhirContext myFhirContext = FhirContext.forR4();
        // Create a TransactionBuilder
        BundleBuilder builder = new BundleBuilder(myFhirContext);
        // Set bundle type to be searchset
        builder.setBundleField("type", "searchset").setBundleField("id", UUID.randomUUID().toString()).setMetaField("lastUpdated", builder.newPrimitive("instant", new Date()));

        // Create bundle entry
        IBase entry = builder.addEntry();

        // Create a Patient to create
        Patient patient = new Patient();
        patient.setActive(true);
        patient.addIdentifier().setSystem("http://foo").setValue("bar");
        builder.addToEntry(entry, "resource", patient);


        // create a
        IBase medicationRequestEntry = builder.addEntry();

        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setStatus(MedicationRequest.MedicationRequestStatus.ACTIVE);
        medicationRequest.setIntent(MedicationRequest.MedicationRequestIntent.ORDER);
        CodeableConcept medication = new CodeableConcept();
        medication.addCoding().setCode("260385009");
        medication.setText("Negative for Chlamydia Trachomatis rRNA");
        medicationRequest.setMedication(medication);
        builder.addToEntry(medicationRequestEntry, "resource", medicationRequest);

        // Add search results
        IBase search = builder.addSearch(entry);
        builder.setSearchField(search, "mode", "match");
        builder.setSearchField(search, "score", builder.newPrimitive("decimal", BigDecimal.ONE));


        System.out.println(builder);
        String bundleJson = ParserResourceUtil.encodeResourceToString(builder.getBundle());
        System.out.println(bundleJson);
    }

    @Test
    public void buildBundleResource2() {
        // Create a patient object
        Patient patient = new Patient();
        patient.addIdentifier().setSystem("http://acme.org/mrns").setValue("12345");
        patient.addName().setFamily("Jameson").addGiven("J").addGiven("Jonah");
        patient.setGender(Enumerations.AdministrativeGender.MALE);

        // Give the patient a temporary UUID so that other resources in
        // the transaction can refer to it
        patient.setId(IdType.newRandomUuid());

        // Create an observation object
        Observation observation = new Observation();
        observation.setStatus(Observation.ObservationStatus.FINAL);
        observation.getCode().addCoding().setSystem("http://loinc.org").setCode("789-8").setDisplay("Erythrocytes [#/volume] in Blood by Automated count");
        observation.setValue(new Quantity().setValue(4.12).setUnit("10 trillion/L").setSystem("http://unitsofmeasure.org").setCode("10*12/L"));

        // The observation refers to the patient using the ID, which is already
        // set to a temporary UUID
        observation.setSubject(new Reference(patient.getIdElement().getValue()));

        // Create a bundle that will be used as a transaction
        Bundle bundle = new Bundle();
        bundle.setType(Bundle.BundleType.TRANSACTION);

        // Add the patient as an entry. This entry is a POST with an
        // If-None-Exist header (conditional create) meaning that it
        // will only be created if there isn't already a Patient with
        // the identifier 12345
        bundle.addEntry().setFullUrl(patient.getIdElement().getValue()).setResource(patient).getRequest().setUrl("Patient").setIfNoneExist("identifier=http://acme.org/mrns|12345").setMethod(Bundle.HTTPVerb.POST);

        // Add the observation. This entry is a POST with no header
        // (normal create) meaning that it will be created even if
        // a similar resource already exists.
        bundle.addEntry().setResource(observation).getRequest().setUrl("Observation").setMethod(Bundle.HTTPVerb.POST);

        // Log the request
        FhirContext ctx = FhirContext.forR4();
        System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

        // Create a client and post the transaction to the server
        IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        Bundle resp = client.transaction().withBundle(bundle).execute();

        // Log the response
        System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(resp));
    }

    @Test
    public void crateBundleResouces() {
        FhirContext ctx = FhirContext.forR4();
        IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");

        Patient patient = new Patient();
        patient.setId("PATIENT-ABC");
        patient.setActive(true);
        client.update().resource(patient).execute();

        Observation observation = new Observation();
        observation.setId("OBSERVATION-ABC");
        observation.setSubject(new Reference("Patient/PATIENT-ABC"));
        observation.setStatus(Observation.ObservationStatus.FINAL);
        client.update().resource(observation).execute();

        Composition composition = new Composition();
        composition.setId("COMPOSITION-ABC");
        composition.setSubject(new Reference("Patient/PATIENT-ABC"));
        composition.addSection().setFocus(new Reference("Observation/OBSERVATION-ABC"));
        client.update().resource(composition).execute();

        Bundle document = client.operation().onInstance("Composition/COMPOSITION-ABC").named("$document").withNoParameters(Parameters.class).returnResourceType(Bundle.class).execute();

        System.out.println(String.format("Document bundle: %s", ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(document)));
    }


    /**
     * 根据HA 给的需求文档进行构建Bundler.
     */
    @Test
    public void testBuilderBundleFromFileJson() {
        FhirContext myFhirContext = FhirContext.forR4();
        // Create a TransactionBuilder
        BundleBuilder builder = new BundleBuilder(myFhirContext);
        // Set bundle type to be searchset
        builder.setBundleField("type", "document").setMetaField("lastUpdated", builder.newPrimitive("instant",
                new Date()));

        // patient entry
        IBase entry = builder.addEntry();
        IParser parser = myFhirContext.newJsonParser();

        Patient patient = parser.parseResource(Patient.class, BundleResourceTest.class.getClassLoader().getResourceAsStream("ha-data/Patient001.json"));
        builder.addToEntry(entry, "resource", patient);

        // PractitionerRole entry
        entry = builder.addEntry();
        PractitionerRole practitionerRole = parser.parseResource(PractitionerRole.class, BundleResourceTest.class.getClassLoader().getResourceAsStream("ha-data/PractitionerRole0001.json"));
        builder.addToEntry(entry, "resource", practitionerRole);

        // MedicationDispense entry
        entry = builder.addEntry();
        MedicationRequest medicationRequest = parser.parseResource(MedicationRequest.class,
                BundleResourceTest.class.getClassLoader().getResourceAsStream("ha-data/MedicationRequest001.json"));
        builder.addToEntry(entry, "resource", medicationRequest);

        // MedicationRequest entry
        entry = builder.addEntry();
        MedicationDispense medicationDispense = parser.parseResource(MedicationDispense.class,
                BundleResourceTest.class.getClassLoader().getResourceAsStream("ha-data/MedicationDispense001.json"));
        builder.addToEntry(entry, "resource", medicationDispense);


        System.out.println(myFhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(builder.getBundle()));
    }

    /**
     * 根据HA 给的需求文档进行构建Bundler.
     */
    @Test
    public void testJsonFile2BundleObject() throws IOException {
        FhirContext myFhirContext = FhirContext.forR4();

        IParser parser = myFhirContext.newJsonParser();

        Bundle bundle = parser.parseResource(Bundle.class, BundleResourceTest.class.getClassLoader().getResourceAsStream("ha-data/Bundler001.json"));
        Assertions.assertNotNull(bundle);
        Assertions.assertTrue(StringUtils.equalsIgnoreCase(bundle.getType().name(), "document"));
        Assertions.assertEquals(bundle.getResourceType().name(), "Bundle");
        Assertions.assertTrue(bundle.getEntry().size() == 4);

    }


}
