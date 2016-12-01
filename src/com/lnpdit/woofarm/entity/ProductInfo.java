package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class ProductInfo implements Serializable {
    private String status;
    private String msg;
    private String image;
    private String name;
    private String price;
    private String shelvestime;
    private String area;
    private String channelid;
    private String ip;
    private String port;
    private String account;
    private String password;
    private String type;
    private String id;
    private String yield;
    private String imgs;

    public ProductInfo() {

    }

    public ProductInfo(String status,String msg,String image, String name, String price, String shelvestime,
            String area, String channelid, String ip,String port, String account, String password,
            String type,String id, String yield, String imgs) {
        this.status = status;
        this.msg = msg;
        this.image = image;
        this.name = name;
        this.price = price;
        this.shelvestime = shelvestime;
        this.area = area;
        this.channelid = channelid;
        this.ip = ip;
        this.port = port;
        this.account = account;
        this.password = password;
        this.type = type;
        this.id = id;
        this.yield = yield;
        this.imgs = imgs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getShelvestime() {
        return shelvestime;
    }

    public void setShelvestime(String shelvestime) {
        this.shelvestime = shelvestime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

}
