package com.ha.fhir.impl.patient.provider;

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

            @Description(shortDefinition = "Search the contents of the resource's data using a filter")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_FILTER)
            StringAndListParam theFtFilter,

            @Description(shortDefinition = "Search the contents of the resource's data using a fulltext search")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_CONTENT)
            StringAndListParam theFtContent,

            @Description(shortDefinition = "Search the contents of the resource's narrative using a fulltext search")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_TEXT)
            StringAndListParam theFtText,

            @Description(shortDefinition = "Search for resources which have the given tag")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_TAG)
            TokenAndListParam theSearchForTag,

            @Description(shortDefinition = "Search for resources which have the given security labels")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_SECURITY)
            TokenAndListParam theSearchForSecurity,

            @Description(shortDefinition = "Search for resources which have the given profile")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_PROFILE)
            UriAndListParam theSearchForProfile,

            @Description(shortDefinition = "Search for resources which have the given source value (Resource.meta.source)")
            @OptionalParam(name = ca.uhn.fhir.rest.api.Constants.PARAM_SOURCE)
            UriAndListParam theSearchForSource,

            @Description(shortDefinition = "Return resources linked to by the given target")
            @OptionalParam(name = "_has")
            HasAndListParam theHas,


            @Description(shortDefinition = "The ID of the resource")
            @OptionalParam(name = "_id")
            TokenAndListParam the_id,


            @Description(shortDefinition = "Whether the patient record is active")
            @OptionalParam(name = "active")
            TokenAndListParam theActive,


            @Description(shortDefinition = "A server defined search that may match any of the string fields in the Address, including line, city, district, state, country, postalCode, and/or text")
            @OptionalParam(name = "address")
            StringAndListParam theAddress,


            @Description(shortDefinition = "A city specified in an address")
            @OptionalParam(name = "address-city")
            StringAndListParam theAddress_city,


            @Description(shortDefinition = "A country specified in an address")
            @OptionalParam(name = "address-country")
            StringAndListParam theAddress_country,


            @Description(shortDefinition = "A postalCode specified in an address")
            @OptionalParam(name = "address-postalcode")
            StringAndListParam theAddress_postalcode,


            @Description(shortDefinition = "A state specified in an address")
            @OptionalParam(name = "address-state")
            StringAndListParam theAddress_state,


            @Description(shortDefinition = "A use code specified in an address")
            @OptionalParam(name = "address-use")
            TokenAndListParam theAddress_use,


            @Description(shortDefinition = "The patient's date of birth")
            @OptionalParam(name = "birthdate")
            DateRangeParam theBirthdate,


            @Description(shortDefinition = "The date of death has been provided and satisfies this search value")
            @OptionalParam(name = "death-date")
            DateRangeParam theDeath_date,


            @Description(shortDefinition = "This patient has been marked as deceased, or as a death date entered")
            @OptionalParam(name = "deceased")
            TokenAndListParam theDeceased,


            @Description(shortDefinition = "A value in an email contact")
            @OptionalParam(name = "email")
            TokenAndListParam theEmail,


            @Description(shortDefinition = "A portion of the family name of the patient")
            @OptionalParam(name = "family")
            StringAndListParam theFamily,


            @Description(shortDefinition = "Gender of the patient")
            @OptionalParam(name = "gender")
            TokenAndListParam theGender,


            @Description(shortDefinition = "Patient's nominated general practitioner, not the organization that manages the record")
            @OptionalParam(name = "general-practitioner", targetTypes = {})
            ReferenceAndListParam theGeneral_practitioner,


            @Description(shortDefinition = "A portion of the given name of the patient")
            @OptionalParam(name = "given")
            StringAndListParam theGiven,


            @Description(shortDefinition = "A patient identifier")
            @OptionalParam(name = "identifier")
            TokenAndListParam theIdentifier,


            @Description(shortDefinition = "Language code (irrespective of use value)")
            @OptionalParam(name = "language")
            TokenAndListParam theLanguage,


            @Description(shortDefinition = "All patients linked to the given patient")
            @OptionalParam(name = "link", targetTypes = {})
            ReferenceAndListParam theLink,


            @Description(shortDefinition = "A server defined search that may match any of the string fields in the HumanName, including family, give, prefix, suffix, suffix, and/or text")
            @OptionalParam(name = "name")
            StringAndListParam theName,


            @Description(shortDefinition = "The organization that is the custodian of the patient record")
            @OptionalParam(name = "organization", targetTypes = {})
            ReferenceAndListParam theOrganization,


            @Description(shortDefinition = "A value in a phone contact")
            @OptionalParam(name = "phone")
            TokenAndListParam thePhone,


            @Description(shortDefinition = "A portion of either family or given name using some kind of phonetic matching algorithm")
            @OptionalParam(name = "phonetic")
            StringAndListParam thePhonetic,


            @Description(shortDefinition = "The value in any kind of telecom details of the patient")
            @OptionalParam(name = "telecom")
            TokenAndListParam theTelecom,

            @RawParam
            Map<String, List<String>> theAdditionalRawParams,

            @Description(shortDefinition = "Only return resources which were last updated as specified by the given range")
            @OptionalParam(name = "_lastUpdated")
            DateRangeParam theLastUpdated,

            @IncludeParam
            Set<Include> theIncludes,

            @IncludeParam(reverse = true)
            Set<Include> theRevIncludes,

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
        return patientResourceRepository.search("11", "male", "lt2022-06-01");
    }
}
