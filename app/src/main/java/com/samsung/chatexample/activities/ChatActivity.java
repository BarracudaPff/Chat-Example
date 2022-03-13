package com.samsung.chatexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samsung.chatexample.R;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.services.DatabaseService;

public class ChatActivity extends AppCompatActivity {
    User currentUser;

    FloatingActionButton floatingActionButton;

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
    }
}