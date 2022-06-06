package com.ha.fhir.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @className: JSONUtil
 * @description: JSON工具.
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
public class JSONUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.writerWithDefaultPrettyPrinter();
        MAPPER.setDateFormat(StdDateFormat.getDateTimeInstance());
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public static ObjectMapper getInstance() {
        return MAPPER;
    }

    public static String toJSON(@NotNull Object obj) {
        Objects.requireNonNull(obj, "obj must be not null");
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(obj.getClass() + " 对象不支持json转化,检查是否有get/set方法");
        }
    }

    public static <T> T parseObject(@NotBlank String json, @NotNull Class<T> target) {
        if (StringUtils.isEmpty(json)) {
            throw new RuntimeException("json must be not null");
        }
        Objects.requireNonNull(target, "target must be not null");
        try {
            return MAPPER.readValue(json, target);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json转换异常");
        }
    }
}
