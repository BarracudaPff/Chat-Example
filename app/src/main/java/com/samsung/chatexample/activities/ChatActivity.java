package com.samsung.chatexample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.samsung.chatexample.R;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.services.DatabaseService;

public class ChatActivity extends AppCompatActivity {
    User currentUser;
    TextView textViewName;

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
                textViewName.setText(user.name);
            }

            @Override
            public void onException(Exception e) {
                Toast.makeText(getBaseContext(), "Can't get user", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onDatabaseError(DatabaseError e) {
                Toast.makeText(getBaseContext(), "Can't get user from firebase", Toast.LENGTH_SHORT).show();
                e.toException().printStackTrace();
            }
        });
    }

    void initViews() {
        textViewName = findViewById(R.id.textViewName);
    }
}