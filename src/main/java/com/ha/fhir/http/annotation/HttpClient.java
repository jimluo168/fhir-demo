package com.ha.fhir.http.annotation;

import java.lang.annotation.*;

/**
 * @className: HttpClient
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpClient {
    /**
     * 请求的基础地址.
     *
     * @return
     */
    String url() default "";
}
