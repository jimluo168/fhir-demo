package com.ha.fhir.http;

/**
 * @className: UnSupportedDataFormatException
 * @description: 不支持的数据格式转换.
 * @author: Jim Luo
 * @date: 2022/6/2
 **/
public class UnSupportedDataFormatException extends RuntimeException {
    public UnSupportedDataFormatException(String message) {
        super(message);
    }
}
