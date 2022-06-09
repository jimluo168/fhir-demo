package com.ha.fhir.modelmapper.pojo;


public class Apple {
    private String id ;
    private String name ;
    private String createAge ;

    private String codes;

    public Apple(){

    }

    public Apple(String id, String name, String createAge,String codes) {
        this.id=id;
        this.name=name;
        this.createAge=createAge;
        this.codes=codes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateAge() {
        return createAge;
    }

    public void setCreateAge(String createAge) {
        this.createAge = createAge;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createAge='" + createAge + '\'' +
                ", codes='" + codes + '\'' +
                '}';
    }
}
