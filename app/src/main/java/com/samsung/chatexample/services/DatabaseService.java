package com.samsung.chatexample.services;

import androidx.annotation.NonNull;

import com.firebase.ui.database.ClassSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.models.domain.MessageD;
import com.samsung.chatexample.models.domain.UserD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class DatabaseService {
    public static DatabaseReference usersRef() {
        return FirebaseDatabase.getInstance()
                .getReference("users");
    }

    public static String addUser(UserD user) {
        DatabaseReference ref = usersRef().push();
        ref.setValue(user);
        return ref.getKey();
    }

    public static void getUser(String id, MyValueEventListener<User> listener) {
        usersRef().child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserD userD = snapshot.getValue(UserD.class);
                if (userD == null) {
                    listener.onException(new NullPointerException("Snapshot is null"));
                    return;
                }

                User user = new User(userD, snapshot.getKey());
                listener.onValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onDatabaseError(error);
            }
        });
    }

    public static void getUsers(MyValueEventListener<List<User>> listener) {
        usersRef().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<User> users = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    UserD userD = child.getValue(UserD.class);
                    users.add(new User(userD, child.getKey()));
                }

                listener.onValue(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public static FirebaseRecyclerOptions<String> getUsersOptions(User user) {
        Query query = usersRef().child(user.id).child("chats");
        ClassSnapshotParser<String> parser = new ClassSnapshotParser<>(String.class);

        return new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(query, parser)
                .build();
    }
}
