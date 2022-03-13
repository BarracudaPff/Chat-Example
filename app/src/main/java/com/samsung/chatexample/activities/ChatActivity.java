package com.samsung.chatexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samsung.chatexample.R;
import com.samsung.chatexample.adapters.LastMessagesAdapter;
import com.samsung.chatexample.adapters.MessageAdapter;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.services.ChatService;
import com.samsung.chatexample.services.DatabaseService;

public class ChatActivity extends AppCompatActivity {
    User currentUser;

    FloatingActionButton floatingActionButton;
    RecyclerView userRecyclerView;

    LastMessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();

        String id = getIntent().getStringExtra("USER_KEY");
        DatabaseService.getUser(id, new MyValueEventListener<User>() {
            @Override
            public void onValue(User user) {
                currentUser = user;

                adapter = new LastMessagesAdapter(DatabaseService.getUsersOptions(currentUser), new MyValueEventListener<String>() {
                    @Override
                    public void onValue(String toId) {
                        DatabaseService.getUser(toId, new MyValueEventListener<User>() {
                            @Override
                            public void onValue(User value) {
                                Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
                                intent.putExtra("DIALOG_WITH", user);
                                intent.putExtra("DIALOG_FROM", value);
                                startActivity(intent);
                            }
                        });
                    }
                });
                adapter.startListening();

                userRecyclerView.setAdapter(adapter);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("!!");
                Intent intent = new Intent(v.getContext(), UserListActivity.class);
                intent.putExtra("DIALOG_FROM", currentUser);
                startActivity(intent);
            }
        });
    }

    void initViews() {
        floatingActionButton = findViewById(R.id.floatingActionButton);
        userRecyclerView = findViewById(R.id.userRecyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        userRecyclerView.setLayoutManager(manager);
    }

    @Override
    protected void onStart() {
        if (adapter != null) {
            adapter.startListening();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        adapter.stopListening();
        super.onStop();
    }
}