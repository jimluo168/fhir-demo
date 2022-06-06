package com.ha.fhir.impl.patient.provider;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.SearchContainedModeEnum;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.*;
import ca.uhn.fhir.rest.server.IResourceProvider;
import com.ha.fhir.BaseResourceProvider;
import com.ha.fhir.api.patient.provider.IPatientResourceProvider;
import com.ha.fhir.api.patient.repository.IPatientResourceRepository;
import com.ha.fhir.config.BaseR4Config;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className: PatientResourceProvider
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/2
 **/
public class PatientResourceProviderImpl extends BaseResourceProvider implements IPatientResourceProvider, IResourceProvider {

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Patient.class;
    }

    @Autowired
    private IPatientResourceRepository patientResourceRepository;

    @Override
    @Read
    public Patient read(HttpServletRequest theRequest, @IdParam IIdType theId, RequestDetails theRequestDetails) {
        return patientResourceRepository.getById(theId.getValue());
    }

    @Override
    @Search(allowUnknownParams = true)
    public Bundle search(
            javax.servlet.http.HttpServletRequest theServletRequest,
            javax.servlet.http.HttpServletResponse theServletResponse,

            ca.uhn.fhir.rest.api.server.RequestDetails theRequestDetails,


            @Description(shortDefinition = "A server defined search that may match any of the string fields in the HumanName, including family, give, prefix, suffix, suffix, and/or text")
            @OptionalParam(name = "name")
            StringAndListParam theName,

            @Description(shortDefinition = "Gender of the patient")
            @OptionalParam(name = "gender")
            TokenAndListParam theGender,

            @Description(shortDefinition = "A patient identifier")
            @OptionalParam(name = "identifier")
            TokenAndListParam theIdentifier,

            @Description(shortDefinition = "The patient's date of birth")
            @OptionalParam(name = "birthdate")
            DateRangeParam theBirthdate,

            @Sort
            SortSpec theSort,

            @ca.uhn.fhir.rest.annotation.Count
            Integer theCount,

            @ca.uhn.fhir.rest.annotation.Offset
            Integer theOffset,

            SummaryEnum theSummaryMode,

            SearchTotalModeEnum theSearchTotalMode,

            SearchContainedModeEnum theSearchContainedMode

    ) {

        return patientResourceRepository.search(theName, theGender, theIdentifier, theBirthdate);
    }
}
