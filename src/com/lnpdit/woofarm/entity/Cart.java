package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private String userid;
    private String cartid;
    private String proid;
    private String name;
    private String price;
    private String count;
    private String thumb;
    private String hj;

    public Cart() {

    }

    public Cart(String userid,String cartid, String proid, String name, String price,
            String count, String thumb, String hj) {
        this.userid = userid;
        this.cartid = cartid;
        this.proid = proid;
        this.name = name;
        this.price = price;
        this.count = count;
        this.thumb = thumb;
        this.hj = hj;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

}
