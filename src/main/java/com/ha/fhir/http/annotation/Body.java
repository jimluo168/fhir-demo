package com.ha.fhir.http.annotation;

import java.lang.annotation.*;

/**
 * @className: Body
 * @description: Http Body修饰符.
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Body {

    String defaultValue() default "";
}
