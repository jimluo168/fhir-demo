package com.ha.fhir.http.annotation;

import java.lang.annotation.*;

/**
 * @className: ResponseBody
 * @description: 修饰HttpClient响应的信息. 包括数据格式、版本等信息.
 * @author: Jim Luo
 * @date: 2022/6/2
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {
    /**
     * 数据格式, FHIR标准JSON格式：fhir+json, FHIR标准XML格式：fhir+xml,普通JSON格式：json,普通xml格式：xml
     *
     * @return
     */
    DataFormat format() default DataFormat.FHIR_JSON;

    public static enum DataFormat {
        FHIR_JSON("fhir+json"),
        FHIR_XML("fhir+xml"),
        JSON("json"),
        XML("xml");

        private String format;

        DataFormat(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}
