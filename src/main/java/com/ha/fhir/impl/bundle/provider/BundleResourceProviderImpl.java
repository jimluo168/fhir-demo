package com.ha.fhir.impl.bundle.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.api.*;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.param.*;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.util.BundleBuilder;
import com.ha.fhir.BaseResourceProvider;
import com.ha.fhir.api.bundle.provider.IBundleResourceProvider;
import com.ha.fhir.api.bundle.service.IBundleResourceService;
import org.hl7.fhir.instance.model.api.IBase;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BundleResourceProviderImpl extends BaseResourceProvider implements IBundleResourceProvider,
        IResourceProvider {
    @Autowired
    private IBundleResourceService bundleResourceService;

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Bundle.class;
    }


    @Override
    @Read
    public Bundle read(HttpServletRequest theRequest, @IdParam IIdType theId, RequestDetails theRequestDetails) {
        return bundleResourceService.read(theId, theRequestDetails);
    }

    @Override
    @Search(allowUnknownParams = true)
    public IBaseResource search(
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


            @Description(shortDefinition = "The first resource in the bundle, if the bundle type is \"document\" - this is a composition, and this parameter provides access to search its contents")
            @OptionalParam(name = "composition", targetTypes = {})
            ReferenceAndListParam theComposition,


            @Description(shortDefinition = "Persistent identifier for the bundle")
            @OptionalParam(name = "identifier")
            TokenAndListParam theIdentifier,


            @Description(shortDefinition = "The first resource in the bundle, if the bundle type is \"message\" - this is a message header, and this parameter provides access to search its contents")
            @OptionalParam(name = "message", targetTypes = {})
            ReferenceAndListParam theMessage,


            @Description(shortDefinition = "When the bundle was assembled")
            @OptionalParam(name = "timestamp")
            DateRangeParam theTimestamp,


            @Description(shortDefinition = "document | message | transaction | transaction-response | batch | batch-response | history | searchset | collection")
            @OptionalParam(name = "type")
            TokenAndListParam theType,

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
        FhirContext myFhirContext = FhirContext.forR4();
        // Create a TransactionBuilder
        BundleBuilder builder = new BundleBuilder(myFhirContext);
        // Set bundle type to be searchset
        builder.setBundleField("type", "document").setMetaField("lastUpdated", builder.newPrimitive("instant",
                new Date()));

        // patient entry
        IBase entry = builder.addEntry();
        IParser parser = myFhirContext.newJsonParser();

        Patient patient = parser.parseResource(Patient.class, getClass().getClassLoader().getResourceAsStream("ha-data" +
                "/Patient001.json"));
        builder.addToEntry(entry, "resource", patient);

        // PractitionerRole entry
        entry = builder.addEntry();
        PractitionerRole practitionerRole = parser.parseResource(PractitionerRole.class, getClass().getClassLoader().getResourceAsStream("ha-data/PractitionerRole0001.json"));
        builder.addToEntry(entry, "resource", practitionerRole);

        // MedicationDispense entry
        entry = builder.addEntry();
        MedicationRequest medicationRequest = parser.parseResource(MedicationRequest.class,
                getClass().getClassLoader().getResourceAsStream("ha-data/MedicationRequest001.json"));
        builder.addToEntry(entry, "resource", medicationRequest);

        // MedicationRequest entry
        entry = builder.addEntry();
        MedicationDispense medicationDispense = parser.parseResource(MedicationDispense.class,
                getClass().getClassLoader().getResourceAsStream("ha-data/MedicationDispense001.json"));
        builder.addToEntry(entry, "resource", medicationDispense);
        return builder.getBundle();
    }

    @Override
    public MethodOutcome create(HttpServletRequest theRequest, @ResourceParam Bundle theResource, @ConditionalUrlParam String theConditional, RequestDetails theRequestDetails) {
        return null;
    }
}
