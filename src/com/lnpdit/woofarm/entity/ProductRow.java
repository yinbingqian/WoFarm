package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class ProductRow implements Serializable {
    private String proid1;
    private String name1;
    private String price1;
    private String thumb1;
    private String proid2;
    private String name2;
    private String price2;
    private String thumb2;

    public ProductRow() {

    }

    public ProductRow(String proid1, String name1, String price1,
            String thumb1, String proid2, String name2, String price2,
            String thumb2) {
        this.proid1 = proid1;
        this.name1 = name1;
        this.price1 = price1;
        this.thumb1 = thumb1;
        this.proid2 = proid2;
        this.name2 = name2;
        this.price2 = price2;
        this.thumb2 = thumb2;
    }

    public String getProid1() {
        return proid1;
    }

    public void setProid1(String proid1) {
        this.proid1 = proid1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getThumb1() {
        return thumb1;
    }

    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }


    public String getProid2() {
        return proid2;
    }

    public void setProid2(String proid2) {
        this.proid2 = proid2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getThumb2() {
        return thumb2;
    }

    public void setThumb2(String thumb2) {
        this.thumb2 = thumb2;
    }

}
