package com.ha.fhir.bundle.controller;

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
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BundleResourceProvider implements IResourceProvider {
    @Override
    public Class<Bundle> getResourceType() {
        return Bundle.class;
    }

    @Read()
    public Bundle read(HttpServletRequest theRequest, @IdParam IIdType theId, RequestDetails theRequestDetails) {

        return null;
    }

    @Search(allowUnknownParams=true)
    public ca.uhn.fhir.rest.api.server.IBundleProvider search(
            javax.servlet.http.HttpServletRequest theServletRequest,
            javax.servlet.http.HttpServletResponse theServletResponse,

            ca.uhn.fhir.rest.api.server.RequestDetails theRequestDetails,

            @Description(shortDefinition="Search the contents of the resource's data using a filter")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_FILTER)
            StringAndListParam theFtFilter,

            @Description(shortDefinition="Search the contents of the resource's data using a fulltext search")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_CONTENT)
            StringAndListParam theFtContent,

            @Description(shortDefinition="Search the contents of the resource's narrative using a fulltext search")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_TEXT)
            StringAndListParam theFtText,

            @Description(shortDefinition="Search for resources which have the given tag")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_TAG)
            TokenAndListParam theSearchForTag,

            @Description(shortDefinition="Search for resources which have the given security labels")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_SECURITY)
            TokenAndListParam theSearchForSecurity,

            @Description(shortDefinition="Search for resources which have the given profile")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_PROFILE)
            UriAndListParam theSearchForProfile,

            @Description(shortDefinition="Search for resources which have the given source value (Resource.meta.source)")
            @OptionalParam(name=ca.uhn.fhir.rest.api.Constants.PARAM_SOURCE)
            UriAndListParam theSearchForSource,

            @Description(shortDefinition="Return resources linked to by the given target")
            @OptionalParam(name="_has")
            HasAndListParam theHas,



            @Description(shortDefinition="The ID of the resource")
            @OptionalParam(name="_id")
            TokenAndListParam the_id,


            @Description(shortDefinition="The first resource in the bundle, if the bundle type is \"document\" - this is a composition, and this parameter provides access to search its contents")
            @OptionalParam(name="composition", targetTypes={  } )
            ReferenceAndListParam theComposition,


            @Description(shortDefinition="Persistent identifier for the bundle")
            @OptionalParam(name="identifier")
            TokenAndListParam theIdentifier,


            @Description(shortDefinition="The first resource in the bundle, if the bundle type is \"message\" - this is a message header, and this parameter provides access to search its contents")
            @OptionalParam(name="message", targetTypes={  } )
            ReferenceAndListParam theMessage,


            @Description(shortDefinition="When the bundle was assembled")
            @OptionalParam(name="timestamp")
            DateRangeParam theTimestamp,


            @Description(shortDefinition="document | message | transaction | transaction-response | batch | batch-response | history | searchset | collection")
            @OptionalParam(name="type")
            TokenAndListParam theType,

            @RawParam
            Map<String, List<String>> theAdditionalRawParams,

            @Description(shortDefinition="Only return resources which were last updated as specified by the given range")
            @OptionalParam(name="_lastUpdated")
            DateRangeParam theLastUpdated,

            @IncludeParam
            Set<Include> theIncludes,

            @IncludeParam(reverse=true)
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
        return null;
    }
}
