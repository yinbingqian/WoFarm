package com.lnpdit.woofarm.entity.eb;

import com.lnpdit.woofarm.entity.ChatMessage;

public class ChatMessageEB {
	private String flag;
	private ChatMessage chatmessage;

	public ChatMessageEB() {

	}

	public ChatMessageEB(String flag, ChatMessage chatmessage) {
		this.flag = flag;
		this.chatmessage = chatmessage;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public ChatMessage getChatmessage() {
		return chatmessage;
	}

	public void setChatmessage(ChatMessage chatmessage) {
		this.chatmessage = chatmessage;
	}

}
