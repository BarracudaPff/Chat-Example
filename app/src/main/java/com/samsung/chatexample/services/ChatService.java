package com.samsung.chatexample.services;

import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.samsung.chatexample.models.application.Message;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.models.domain.DialogD;
import com.samsung.chatexample.models.domain.DialogMetadataD;
import com.samsung.chatexample.models.domain.MessageD;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

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

    public static Task<Void> sendMessage(String text, User currentUser, User toUser) {
        MessageD message = new MessageD(text, false, false, currentUser.id, new Date().getTime());
//        ...
        return dialogsRef(currentUser, toUser).child("messages").push().setValue(message);
    }

    public static Task<Void> createDialog(User currentUser, User toUser) {
        DialogD dialog = new DialogD(
                new DialogMetadataD(new Date().getTime()),
                new HashMap<String, MessageD>()
        );
        return dialogsRef(currentUser, toUser).setValue(dialog);
//        ...
    }

    public static FirebaseRecyclerOptions<Message> getUserOptions(User currentUser, User toUser) {
        Query query = dialogsRef(currentUser, toUser).child("messages");
        ClassSnapshotParser<Message> parser = new ClassSnapshotParser<>(Message.class);

        return new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(query, parser)
                .build();
    }
}
