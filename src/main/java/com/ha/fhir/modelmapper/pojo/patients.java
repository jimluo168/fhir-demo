package com.ha.fhir.modelmapper.pojo;

public class patients {
    private String id;

    private String system;

    private String codes;

    private String texts;

    private String name;

    private String genders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenders() {
        return genders;
    }

    public void setGenders(String genders) {
        this.genders = genders;
    }

    @Override
    public String toString() {
        return "patientDTO{" +
                "id=" + id +
                ", system='" + system + '\'' +
                ", codes='" + codes + '\'' +
                ", texts='" + texts + '\'' +
                ", name='" + name + '\'' +
                ", genders='" + genders + '\'' +
                '}';
    }
}
