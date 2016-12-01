package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class ProductByClass implements Serializable {
    private String image;
    private String id;
    private String name;
    private String price;

    public ProductByClass() {

    }

    public ProductByClass(String image, String id, String name, String price) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.price = price;
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
