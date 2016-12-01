package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class IndexAdvertise implements Serializable {
    private String image;
    private String linkaddress;

    public IndexAdvertise() {

    }

    public IndexAdvertise(String image, String linkaddress) {
        this.image = image;
        this.linkaddress = linkaddress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkaddress() {
        return linkaddress;
    }

    public void setLinkaddress(String linkaddress) {
        this.linkaddress = linkaddress;
    }


}
