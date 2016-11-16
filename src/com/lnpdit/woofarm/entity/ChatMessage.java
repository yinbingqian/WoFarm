package com.lnpdit.woofarm.entity;

public class ChatMessage {
	public static final int MESSAGE_O = 0;
	public static final int MESSAGE_T = 1;

	// private String msg_id;
	private int status;// 0：未读；1：已读
	private String content1;// 文本消息
	private byte[] content2;// 语音消息
	private String content3_title;// 新闻消息标题
	private String content3_content;// 新闻消息内容
	private String content3_url;// 新闻消息链接
	private String content3_img;// 新闻消息图片
	private int msg_type;// 0：文本消息；1：语音消息；2：新闻消息
	private int msg_direction;// 0：发送；1：接收
	private String msg_send_date;// 消息发送时间
	private String sender_id;// 发送目标id
	private String login_id;// 发送者id
	private UserInfo userinfo;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public byte[] getContent2() {
		return content2;
	}

	public void setContent2(byte[] content2) {
		this.content2 = content2;
	}

	public int getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}

	public int getMsg_direction() {
		return msg_direction;
	}

	public void setMsg_direction(int msg_direction) {
		this.msg_direction = msg_direction;
	}

	public String getMsg_send_date() {
		return msg_send_date;
	}

	public void setMsg_send_date(String msg_send_date) {
		this.msg_send_date = msg_send_date;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getContent3_title() {
		return content3_title;
	}

	public void setContent3_title(String content3_title) {
		this.content3_title = content3_title;
	}

	public String getContent3_content() {
		return content3_content;
	}

	public void setContent3_content(String content3_content) {
		this.content3_content = content3_content;
	}

	public String getContent3_url() {
		return content3_url;
	}

	public void setContent3_url(String content3_url) {
		this.content3_url = content3_url;
	}

	public String getContent3_img() {
		return content3_img;
	}

	public void setContent3_img(String content3_img) {
		this.content3_img = content3_img;
	}

	public String getSender_id() {
		return sender_id;
	}

	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

}
