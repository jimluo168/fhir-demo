package com.ha.fhir.http.annotation;

import java.lang.annotation.*;

/**
 * @className: Param
 * @description: Http 参数修饰符.
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {
    /**
     * 参数的名称.
     *
     * @return
     */
    String name() default "";

    /**
     * 参数的默认值.
     *
     * @return
     */
    String defaultValue() default "";
}
