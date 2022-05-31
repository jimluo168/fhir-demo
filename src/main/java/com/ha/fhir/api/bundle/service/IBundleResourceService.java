package com.ha.fhir.api.bundle.service;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;

public interface IBundleResourceService {
    Bundle read(IIdType theId, RequestDetails theRequestDetails);
}
