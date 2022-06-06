package com.ha.fhir.httpclient;

import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.DateRangeParam;
import com.ha.fhir.api.bundle.repository.IBundleResourceRepository;
import com.ha.fhir.api.patient.repository.IPatientResourceRepository;
import com.ha.fhir.http.HttpClientSession;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: HttpClientTest
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
//@SpringBootTest
public class HttpClientTest {

    @Test
    public void testGetById() {
        System.setProperty("repository.httpclient.server1", "http://hapi-fhir.apjcorp.com/fhir");
        IBundleResourceRepository repository = HttpClientSession.getMapper(IBundleResourceRepository.class);
        Bundle bundle = repository.getById("1");
        Assertions.assertNull(bundle);
    }

    @Test
    public void testSearch() {
        System.setProperty("repository.httpclient.server1", "http://hapi-fhir.apjcorp.com/fhir");
        IBundleResourceRepository repository = HttpClientSession.getMapper(IBundleResourceRepository.class);
        Bundle bundle = repository.search("233","male", "lt2022-06-01");
        Assertions.assertNull(bundle);
    }

    @Test
    public void testPatinetGetById() {
        System.setProperty("repository.httpclient.server1", "http://hapi-fhir.apjcorp.com/fhir");
        IPatientResourceRepository repository = HttpClientSession.getMapper(IPatientResourceRepository.class);
        Patient patient = repository.getById("1");
        Assertions.assertNull(patient);
    }

    @Test
    public void testPatientSearch() {
        System.setProperty("repository.httpclient.server1", "http://hapi-fhir.apjcorp.com/fhir");
        IPatientResourceRepository repository = HttpClientSession.getMapper(IPatientResourceRepository.class);
        Bundle bundle = repository.search("233","male", "lt2022-06-01");
        Assertions.assertNull(bundle);
    }

}
