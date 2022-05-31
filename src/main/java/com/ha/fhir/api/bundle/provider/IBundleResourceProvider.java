package com.ha.fhir.api.bundle.provider;


import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.*;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.*;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className: IBundleResourceProvider
 * @description: Bundle Resource的服务提供方接口。
 * @author: Jim Luo
 * @date: 2022/5/31
 **/
public interface IBundleResourceProvider {

    /**
     * 根据ID获取Resource。
     *
     * @param theRequest
     * @param theId
     * @param theRequestDetails
     * @return
     */
    Bundle read(HttpServletRequest theRequest, IIdType theId, RequestDetails theRequestDetails);

    IBaseResource search(
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


            ReferenceAndListParam theComposition,


            TokenAndListParam theIdentifier,


            ReferenceAndListParam theMessage,


            DateRangeParam theTimestamp,


            TokenAndListParam theType,

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

    MethodOutcome create(
            HttpServletRequest theRequest,
            Bundle theResource,
            String theConditional,
            RequestDetails theRequestDetails);
}