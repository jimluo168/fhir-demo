package com.ha.fhir.api.bundle.repository;

import ca.uhn.fhir.rest.param.DateRangeParam;
import com.ha.fhir.http.annotation.Get;
import com.ha.fhir.http.annotation.HttpClient;
import com.ha.fhir.http.annotation.Param;
import com.ha.fhir.http.annotation.PathParam;
import org.hl7.fhir.r4.model.Bundle;

import java.util.Date;

/**
 * @className: IBundleResourceRepository
 * @description: Bundle Resource的数据存储层.
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
@HttpClient(url = "${repository.httpclient.server1}/Bundle")
public interface IBundleResourceRepository {

    @Get(path = "/{id}")
    Bundle getById(@PathParam("id") String id);

    @Get(path = "")
    Bundle search(@Param(name = "identifier") String identifier,
                  @Param(name = "gander") String gander,
                  @Param(name = "birthdate") String birthdate);


}
