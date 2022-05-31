package com.ha.fhir.impl.bundle.service;

import ca.uhn.fhir.rest.api.server.RequestDetails;
import com.ha.fhir.api.bundle.service.IBundleResourceService;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @className: BundleResourceServiceImpl
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/5/31
 **/
@Service
public class BundleResourceServiceImpl implements IBundleResourceService {
    private static final Logger logger = LoggerFactory.getLogger(BundleResourceServiceImpl.class);

    @Override
    public Bundle read(IIdType theId, RequestDetails theRequestDetails) {
        logger.info("bundle resource service read method");
        return null;
    }
}
