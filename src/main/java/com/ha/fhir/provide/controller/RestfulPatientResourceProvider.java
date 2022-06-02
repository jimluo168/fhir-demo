package com.ha.fhir.provide.controller;

import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.ha.fhir.BaseProvider;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;

import java.util.*;

public class RestfulPatientResourceProvider extends BaseProvider implements IResourceProvider {
    private Map<String, Patient> myPatients = new HashMap();

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Patient.class;
    }

    public RestfulPatientResourceProvider(){
        Patient patient = new Patient();
        patient.setId("1");
        patient.addIdentifier();
        patient.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        patient.getIdentifier().get(0).setValue("00002");
        patient.addName().setFamily("Test");
        patient.getName().get(0).addGiven("PatientOne");
        patient.setGender(Enumerations.AdministrativeGender.FEMALE);
        patient.addGeneralPractitioner().setReference("ssss");
        myPatients.put(patient.getId(),patient);
    }

    @Read()
    public Patient getResourceById(@IdParam IdType theId) {
        Patient patient = myPatients.get(theId.getIdPart());
        if (patient == null) {
            throw new ResourceNotFoundException(theId);
        }
        return patient;
    }

    @Search()
    public List<Patient> search() {
        List<Patient> list = new ArrayList();
        list.addAll(myPatients.values());
        return list;
    }

    @Search
    public List<Patient> search(@RequiredParam(name = Patient.SP_FAMILY) StringParam theParam) {
        List<Patient> list = new ArrayList();
        // Loop through the patients looking for matches
        for (Patient patient : myPatients.values()) {
            String familyName = patient.getNameFirstRep().getFamily().toLowerCase();
            if(familyName.contains(theParam.getValue().toLowerCase()) == false) {
                continue;
            }
            list.add(patient);
        }
        return list;
    }

}
