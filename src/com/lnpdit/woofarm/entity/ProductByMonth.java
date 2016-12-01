package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class ProductByMonth implements Serializable {
    private String date;
    private String name;

    public ProductByMonth() {

    }

    public ProductByMonth(String date, String name) {
        this.date = date;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
