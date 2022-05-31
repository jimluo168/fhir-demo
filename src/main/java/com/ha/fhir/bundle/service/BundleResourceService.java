package com.ha.fhir.bundle.service;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.instance.model.api.IIdType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BundleResourceService {

    private static final Logger logger = LoggerFactory.getLogger(BundleResourceService.class);

    public void read(IIdType theId, RequestDetails theRequestDetails) {
        logger.info("read service method id:{}, requestDetails:{}", theId, theRequestDetails);
    }
}
