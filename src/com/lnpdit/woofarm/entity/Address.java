package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private String userid;
    private String addid;
    private String ifdefault;
    private String name;
    private String phone;
    private String addinfo;

    public Address() {

    }

    public Address(String userid, String addid, String ifdefault, String name,
            String phone, String addinfo) {
        this.userid = userid;
        this.addid = addid;
        this.ifdefault = ifdefault;
        this.name = name;
        this.phone = phone;
        this.addinfo = addinfo;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAddid() {
        return addid;
    }

    public void setAddid(String addid) {
        this.addid = addid;
    }

    public String getIfdefault() {
        return ifdefault;
    }

    public void setIfdefault(String ifdefault) {
        this.ifdefault = ifdefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddinfo() {
        return addinfo;
    }

    public void setAddinfo(String addinfo) {
        this.addinfo = addinfo;
    }
}
