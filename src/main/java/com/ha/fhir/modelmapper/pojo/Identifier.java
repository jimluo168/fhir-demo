package com.ha.fhir.modelmapper.pojo;

public class Identifier {
    private String use;
    private String system;

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "use='" + use + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}
