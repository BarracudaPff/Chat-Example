package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.MessageD;

import java.util.Date;

public class Message extends MessageD {
    public User from;
    public User to;
    public Date createDate;

    public Message(MessageD messageD, User from, User to) {
        this.createDate = new Date(messageD.createDate);
        this.from = from;
        this.to = to;

        text = messageD.text;
        isRead = messageD.isRead;
        isSent = messageD.isSent;
        fromID = messageD.fromID;
    }
}
