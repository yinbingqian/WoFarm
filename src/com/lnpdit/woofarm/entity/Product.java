package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Product implements Serializable {
    private String proid;
    private String name;
    private String price;
    private String uptime;
    private String place;
    private String number;
    private String thumb;
    private String pic;
    private String ip;
    private String port;
    private String username;
    private String password;
    private String chno;

    public Product() {

    }

    public Product(String proid, String name, String price, String uptime,
            String place, String number, String thumb, String pic, String ip,
            String port, String username, String password, String chno) {
        this.proid = proid;
        this.name = name;
        this.price = price;
        this.uptime = uptime;
        this.place = place;
        this.number = number;
        this.thumb = thumb;
        this.pic = pic;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.chno = chno;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
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

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getChno() {
        return chno;
    }

    public void setChno(String chno) {
        this.chno = chno;
    }
}
