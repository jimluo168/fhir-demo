package com.ha.fhir.patient.controller;

import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.primitive.UriDt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "The Patient FHIR resource type")
@RestController
@RequestMapping("/Patient")
public class PatientController {

    @ApiOperation(value = "Read Patient instance")
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable(value = "id") String id) {
        Patient patient = new Patient();
        patient.addIdentifier();
        patient.getIdentifier().get(0).setSystem(new UriDt("urn:hapitest:mrns").toString());
        patient.getIdentifier().get(0).setValue("00002");
        patient.getName().add(new HumanName());
        patient.getName().get(0).addGiven("PatientOne");
        patient.setGender(Enumerations.AdministrativeGender.FEMALE);
        patient.setBirthDate(new Date());
        return patient;
    }
}
