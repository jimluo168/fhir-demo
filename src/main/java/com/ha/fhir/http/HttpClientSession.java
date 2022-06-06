package com.ha.fhir.http;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @className: HttpClientSession
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
public class HttpClientSession {

    public static <T> T getMapper(Class<T> interfaceType) {
        InvocationHandler handler = new HttpClientProxy(interfaceType);
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(),
                new Class[]{interfaceType},
                handler);
    }
}
