package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private String id;
    private String orderid;
    private String consigneerealname;
    private String consigneeaddress;
    private String consigneephone;
    private String paymenttype;
    private String orderdate;
    private String orderstate;
    private String productimg;
    private String productname;
    private String productprice;
    private String productnum;

//  public Order(String id,String orderid, String consigneerealname, String consigneeaddress, String consigneephone,
//          String paymenttype, String orderdate, String orderstate, String productimg, String productname, String productprice, String productnum) {
//      this.id = id;
//      this.orderid = orderid;
//      this.consigneerealname = consigneerealname;
//      this.consigneeaddress = consigneeaddress;
//      this.consigneephone = consigneephone;
//      this.paymenttype = paymenttype;
//      this.orderdate = orderdate;
//      this.orderstate = orderstate;
//      this.productimg = productimg;
//      this.productname = productname;
//      this.productprice = productprice;
//      this.productnum = productnum;
//  }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public String getConsigneerealname() {
        return consigneerealname;
    }
    public void setConsigneerealname(String consigneerealname) {
        this.consigneerealname = consigneerealname;
    }
    public String getConsigneeaddress() {
        return consigneeaddress;
    }
    public void setConsigneeaddress(String consigneeaddress) {
        this.consigneeaddress = consigneeaddress;
    }
    public String getConsigneephone() {
        return consigneephone;
    }
    public void setConsigneephone(String consigneephone) {
        this.consigneephone = consigneephone;
    }
    public String getPaymenttype() {
        return paymenttype;
    }
    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }
    public String getOrderdate() {
        return orderdate;
    }
    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }
    public String getOrderstate() {
        return orderstate;
    }
    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }
    public String getProductimg() {
        return productimg;
    }
    public void setProductimg(String productimg) {
        this.productimg = productimg;
    }
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public String getProductprice() {
        return productprice;
    }
    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
    public String getProductnum() {
        return productnum;
    }
    public void setProductnum(String productnum) {
        this.productnum = productnum;
    }


}
