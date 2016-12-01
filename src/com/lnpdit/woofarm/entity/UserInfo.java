package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String userid;
    private String username;
	private String headpic;
	
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHeadpic() {
        return headpic;
    }
    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

}
