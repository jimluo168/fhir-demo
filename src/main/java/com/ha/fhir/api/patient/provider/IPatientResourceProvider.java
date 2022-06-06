package com.ha.fhir.api.patient.provider;

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.SearchContainedModeEnum;
import ca.uhn.fhir.rest.api.SearchTotalModeEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.SummaryEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.*;
import com.ha.fhir.http.annotation.Get;
import com.ha.fhir.http.annotation.Param;
import com.ha.fhir.http.annotation.PathParam;
import com.ha.fhir.http.annotation.ResponseBody;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className: IPatientResourceProvider
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/2
 **/
public interface IPatientResourceProvider {
    Patient read(HttpServletRequest theRequest, IIdType theId, RequestDetails theRequestDetails);

    Bundle search(
            javax.servlet.http.HttpServletRequest theServletRequest,
            javax.servlet.http.HttpServletResponse theServletResponse,

            ca.uhn.fhir.rest.api.server.RequestDetails theRequestDetails,

            StringAndListParam theFtFilter,

            StringAndListParam theFtContent,

            StringAndListParam theFtText,

            TokenAndListParam theSearchForTag,

            TokenAndListParam theSearchForSecurity,

            UriAndListParam theSearchForProfile,

            UriAndListParam theSearchForSource,

            HasAndListParam theHas,


            TokenAndListParam the_id,


            TokenAndListParam theActive,


            StringAndListParam theAddress,


            StringAndListParam theAddress_city,


            StringAndListParam theAddress_country,


            StringAndListParam theAddress_postalcode,


            StringAndListParam theAddress_state,


            TokenAndListParam theAddress_use,


            DateRangeParam theBirthdate,


            DateRangeParam theDeath_date,


            TokenAndListParam theDeceased,


            TokenAndListParam theEmail,


            StringAndListParam theFamily,


            TokenAndListParam theGender,


            ReferenceAndListParam theGeneral_practitioner,


            StringAndListParam theGiven,


            TokenAndListParam theIdentifier,


            TokenAndListParam theLanguage,


            ReferenceAndListParam theLink,


            StringAndListParam theName,


            ReferenceAndListParam theOrganization,


            TokenAndListParam thePhone,


            StringAndListParam thePhonetic,


            TokenAndListParam theTelecom,

            Map<String, List<String>> theAdditionalRawParams,

            DateRangeParam theLastUpdated,

            Set<Include> theIncludes,

            Set<Include> theRevIncludes,

            SortSpec theSort,

            Integer theCount,

            Integer theOffset,

            SummaryEnum theSummaryMode,

            SearchTotalModeEnum theSearchTotalMode,

            SearchContainedModeEnum theSearchContainedMode
    );
}
