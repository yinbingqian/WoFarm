package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private String image;
    private String id;
    private String name;
    private String price;
    private String quantity;
    private String totalprice;
    private String shopid;
    
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
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
    public String getShopid() {
        return shopid;
    }
    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    
}
