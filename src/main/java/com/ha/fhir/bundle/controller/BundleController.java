//package com.ha.fhir.bundle.controller;
//
//import ca.uhn.fhir.rest.api.MethodOutcome;
//import com.ha.fhir.bundle.service.BundleService;
//import com.ha.fhir.util.FhirClient4R4Util;
//import com.ha.fhir.util.ParserResourceUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.hl7.fhir.r4.model.Bundle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//
//@Api(tags = "The Bundle FHIR resource type")
//@RestController
//@RequestMapping("/Bundle")
//public class BundleController {
//
//    @Value("${hapi.fhir.serverbase}")
//    private String fhirServerBase;
//    @Autowired
//    private BundleService bundleService;
//
//    @ApiOperation(value = "Read Bundle instance")
//    @GetMapping("/{id}")
//    public String getBundleById(@ApiParam(value = "resource ID", required = true) @PathVariable("id") String id) {
//        Bundle bundle = FhirClient4R4Util.getResourceById(fhirServerBase, id, Bundle.class, Bundle.class);
//        return ParserResourceUtil.encodeResourceToString(bundle);
//    }
//
//    @ApiOperation(value = "create a new Bundle instance")
//    @PostMapping
//    public String postBundle(@RequestBody String bundleStr) {
//        Bundle bundle = ParserResourceUtil.parseResource(Bundle.class, bundleStr);
//        MethodOutcome outcome = FhirClient4R4Util.createResource(fhirServerBase, bundle);
//        return ParserResourceUtil.encodeResourceToString(outcome.getResource());
//    }
//
//
//    @ApiOperation(value = "Put an existing Bundle instance,or create using a client-assigned ID")
//    @PutMapping("/{id}")
//    public String putBundleById(@ApiParam(value = "resource ID", required = true) @PathVariable("id") String id,
//                                @RequestBody Bundle bundle) {
//        return null;
//    }
//
//    @ApiOperation(value = "delete a resource instance")
//    @DeleteMapping("/{id}")
//    public String deleteBundleById(@ApiParam(value = "resource ID", required = true) @PathVariable("id") String id) {
//        return null;
//    }
//
//    @ApiOperation(value = "search for Bundle instances")
//    @GetMapping("")
//    public Map<String, Object> search(BundleResourceParam param) {
//
//        return null;
//    }
//}
