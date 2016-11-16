package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private String checknum;
	private String checksta;
	private String headpic;
	private String id;
	private String level;
	private String mark;
	private String name;
	private String prestige;
	private String rank;
	private String realname;
	private String birth;
	private String sex;
	private String attuser;
	private String newprestige;
	private String rewardmark;

	public UserInfo() {

	}

	public UserInfo(String checknum, String checksta, String headpic, String id, String level, String mark, String name, String prestige, String rank, String realname, String newprestige, String rewardmark) {
		this.checknum = checknum;
		this.checksta = checksta;
		this.headpic = headpic;
		this.id = id;
		this.level = level;
		this.mark = mark;
		this.name = name;
		this.prestige = prestige;
		this.rank = rank;
		this.realname = realname;
		this.newprestige = newprestige;
		this.rewardmark = rewardmark;
	}

	public String getchecknum() {
		return checknum;
	}

	public void setchecknum(String checknum) {
		this.checknum = checknum;
	}

	public String getchecksta() {
		return checksta;
	}

	public void setchecksta(String checksta) {
		this.checksta = checksta;
	}

	public String getheadpic() {
		return headpic;
	}

	public void setheadpic(String headpic) {
		this.headpic = headpic;
	}
	
	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
	
	public String getlevel() {
		return level;
	}

	public void setlevel(String level) {
		this.level = level;
	}
	
	public String getmark() {
		return mark;
	}

	public void setmark(String mark) {
		this.mark = mark;
	}
	
	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
	
	public String getprestige() {
		return prestige;
	}

	public void setprestige(String prestige) {
		this.prestige = prestige;
	}
	
	public String getrank() {
		return rank;
	}

	public void setrank(String rank) {
		this.rank = rank;
	}
	
	public String getrealname() {
		return realname;
	}

	public void setrealname(String realname) {
		this.realname = realname;
	}
	
	public String getbirth() {
		return birth;
	}

	public void setbirth(String birth) {
		this.birth = birth;
	}
	
	public String getsex() {
		return sex;
	}

	public void setsex(String sex) {
		this.sex = sex;
	}
	
	public String getattuser() {
		return attuser;
	}

	public void setattuser(String attuser) {
		this.attuser = attuser;
	}

	public String getNewprestige() {
		return newprestige;
	}

	public void setNewprestige(String newprestige) {
		this.newprestige = newprestige;
	}

	public String getRewardmark() {
		return rewardmark;
	}

	public void setRewardmark(String rewardmark) {
		this.rewardmark = rewardmark;
	}

}
