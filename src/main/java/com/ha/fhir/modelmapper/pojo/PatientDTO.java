package com.ha.fhir.modelmapper.pojo;

public class PatientDTO {
    private String id;

    private Identifier identifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id='" + id + '\'' +
                ", identifier=" + identifier +
                '}';
    }
}
