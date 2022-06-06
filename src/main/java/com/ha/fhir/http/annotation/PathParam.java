package com.ha.fhir.http.annotation;

import java.lang.annotation.*;

/**
 * @className: PathParam
 * @description: 地址参数, 类似SpringMVC org.springframework.web.bind.annotation.PathVariable
 * @author: Jim Luo
 * @date: 2022/6/2
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathParam {
    String value() default "";

    boolean required() default true;
}
