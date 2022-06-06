package com.ha.fhir.http.annotation;

import java.lang.annotation.*;

/**
 * @className: Post
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Post {

    String path() default "";
}
