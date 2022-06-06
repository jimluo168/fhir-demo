package com.ha.fhir.api.patient.repository;

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
    Bundle search(@Param(name = "identifier") String identifier,
                  @Param(name = "gender") String gender,
                  @Param(name = "birthdate") String birthdate);


}
