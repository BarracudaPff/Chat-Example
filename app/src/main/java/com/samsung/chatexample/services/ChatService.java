package com.samsung.chatexample.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samsung.chatexample.models.application.Dialog;
import com.samsung.chatexample.models.application.Message;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.models.domain.DialogD;
import com.samsung.chatexample.models.domain.DialogMetadataD;
import com.samsung.chatexample.models.domain.MessageD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChatService {
    public static DatabaseReference dialogsRef() {
        return FirebaseDatabase.getInstance()
                .getReference("chat")
                .child("dialogs");
    }

    public static DatabaseReference dialogsRef(User userA, User userB) {
        String[] strings = new String[]{userA.id, userB.id};
        Arrays.sort(strings);
        String chatId = strings[0] + "-" + strings[1];

        return dialogsRef().child(chatId);
    }

    public static void sendMessage(String text, User currentUser, User toUser) {
        MessageD message = new MessageD(text, false, false, currentUser.id, new Date().getTime());
//        ...
    }

    public static void createDialog(User currentUser, User toUser) {
        DialogD dialog = new DialogD(
                new DialogMetadataD(new Date().getTime()),
                new HashMap<String, MessageD>()
        );
//        ...
    }
}
