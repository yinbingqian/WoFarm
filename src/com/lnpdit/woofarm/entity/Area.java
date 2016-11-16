package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Area implements Serializable {
    private String areaid;
    private String img;
    private String name;

    public Area() {

    }

    public Area(String areaid, String name) {
        this.areaid = areaid;
        this.name = name;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
