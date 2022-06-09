package com.ha.fhir.modelmapper.pojo;

public class AppleDTO {
    private String name ;
    private String create_age ;

    private AppleVo appleVo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_age() {
        return create_age;
    }

    public void setCreate_age(String create_age) {
        this.create_age = create_age;
    }

    public AppleVo getAppleVo() {
        return appleVo;
    }

    public void setAppleVo(AppleVo appleVo) {
        this.appleVo = appleVo;
    }

    @Override
    public String toString() {
        return "AppleDTO{" +
                "name='" + name + '\'' +
                ", create_age='" + create_age + '\'' +
                ", appleVo=" + appleVo +
                '}';
    }
}
