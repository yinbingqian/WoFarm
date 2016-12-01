package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class PreProduct implements Serializable {
    private String image;
    private String id;
    private String price;
    private String name;

    public PreProduct() {

    }

    public PreProduct(String image, String id, String price, String name) {
        this.image = image;
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
