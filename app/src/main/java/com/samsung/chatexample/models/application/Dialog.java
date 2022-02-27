package com.samsung.chatexample.models.application;

import com.samsung.chatexample.models.domain.DialogD;

import java.util.ArrayList;

public class Dialog extends DialogD {
    public String id;
    public User from;
    public User to;
    public ArrayList<Message> messages;
    public DialogMetadata metadata;

    public Dialog(User from, User to, ArrayList<Message> messages, DialogMetadata metadata, String id) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.messages = messages;
        this.metadata = metadata;
    }
}
