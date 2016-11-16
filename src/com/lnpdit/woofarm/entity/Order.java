package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private String userid;
    private String orderid;
    private String proid;
    private String type;
    private String name;
    private String price;
    private String thumb;
    private String hj;
    private String result;

    public Order() {

    }

    public Order(String userid,String orderid, String proid, String type, String name,
            String price, String thumb, String hj, String result) {
        this.userid = userid;
        this.orderid = orderid;
        this.proid = proid;
        this.type = type;
        this.name = name;
        this.price = price;
        this.thumb = thumb;
        this.hj = hj;
        this.result = result;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
