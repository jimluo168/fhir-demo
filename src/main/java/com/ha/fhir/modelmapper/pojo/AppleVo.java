package com.ha.fhir.modelmapper.pojo;

public class AppleVo {
    private String name ;
    private String id ;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AppleVo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
