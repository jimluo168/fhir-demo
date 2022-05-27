package com.ha.fhir.demo.controller;

import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/demo")
public class DemoController implements IResourceProvider {

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return null;
    }
}
