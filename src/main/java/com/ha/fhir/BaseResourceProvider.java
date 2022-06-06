package com.ha.fhir;

import ca.uhn.fhir.context.FhirContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @className: BaseResourceProvider
 * @description: Resource服务提供方的基础类。
 * @author: Jim Luo
 * @date: 2022/5/31
 */
public abstract class BaseResourceProvider {
    private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(BaseResourceProvider.class);
    @Autowired
    private FhirContext myContext;

    public void setContext(FhirContext theContext) {
        myContext = theContext;
    }

    public FhirContext getContext() {
        return myContext;
    }
}
