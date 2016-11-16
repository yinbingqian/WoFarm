package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Classify implements Serializable {
    private String claid;
    private String name;
    private String type;

    public Classify() {

    }

    public Classify(String claid, String name, String type) {
        this.claid = claid;
        this.name = name;
        this.type = type;
    }

    public String getClaid() {
        return claid;
    }

    public void setClaid(String claid) {
        this.claid = claid;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
