package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Area implements Serializable {
    private String areaid;
    private String address;
    private String img;

    public Area() {

    }

    public Area(String areaid, String address, String img) {
        this.areaid = areaid;
        this.address = address;
        this.img = img;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
