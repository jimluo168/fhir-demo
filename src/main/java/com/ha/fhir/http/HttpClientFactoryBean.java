package com.ha.fhir.http;

import org.springframework.beans.factory.FactoryBean;

/**
 * @className: HttpClientFactory
 * @description: 接口实例工厂，这里主要是用于提供接口的实例对象.
 * @author: Jim Luo
 * @date: 2022/6/2
 **/
public class HttpClientFactoryBean<T> implements FactoryBean<T> {
    private Class<T> interfaceType;

    public HttpClientFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        return HttpClientSession.getMapper(interfaceType);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
