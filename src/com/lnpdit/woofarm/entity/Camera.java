package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Camera implements Serializable {
    private String areaid;
    private String cameraid;
    private String img;
    private String name;

    public Camera() {

    }

    public Camera(String areaid, String cameraid, String name) {
        this.cameraid = cameraid;
        this.name = name;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getCameraid() {
        return cameraid;
    }

    public void setCameraid(String cameraid) {
        this.cameraid = cameraid;
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
