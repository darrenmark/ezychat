package com.darren.webchat.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Message implements IsSerializable {
    private MessageType messageType;
    private String sendTo;
    private String from;
    private String data;


	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public static Message create(MessageType messageType) {
        Message message = new Message();
        message.setMessageType(messageType);
        return message;
    }

    public static Message create(MessageType messageType, String text) {
        Message message = create(messageType);
        message.setData(text);
        return message;
    }

}
