package com.ha.fhir.api.patient.repository;

import ca.uhn.fhir.rest.param.DateRangeParam;
import ca.uhn.fhir.rest.param.StringAndListParam;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import com.ha.fhir.http.annotation.*;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

/**
 * @className: IBundleResourceRepository
 * @description: Bundle Resource的数据存储层.
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
@HttpClient(url = "${repository.httpclient.server1}/Patient")
public interface IPatientResourceRepository {

    @Get(path = "/{id}")
    @ResponseBody(format = ResponseBody.DataFormat.FHIR_JSON)
    Patient getById(@PathParam("id") String id);

    @Get(path = "")
    @ResponseBody(format = ResponseBody.DataFormat.FHIR_JSON)
    Bundle search(@Param(name = "identifier") StringAndListParam identifier,
                  @Param(name = "gender") TokenAndListParam gender,
                  @Param(name = "identifier") TokenAndListParam theIdentifier,
                  @Param(name = "birthdate") DateRangeParam birthdate);


}
