package com.samsung.chatexample.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.samsung.chatexample.R;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.services.ChatService;
import com.samsung.chatexample.services.DatabaseService;

public class DialogActivity extends AppCompatActivity {
    RecyclerView chatView;
    Button sendView;
    EditText editTextView;

    User currentUser;
    User toUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initUsers();

        initViews();

        sendView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextView.getText().toString();
                if (text.isEmpty()) return;

                ChatService.sendMessage(text, currentUser, toUser);
            }
        });
    }

    void initUsers() {
        toUser = (User) getIntent().getSerializableExtra("DIALOG_WITH");
        currentUser = (User) getIntent().getSerializableExtra("DIALOG_FROM");
    }

    public void initViews() {
        chatView = findViewById(R.id.dialogChatView);
        sendView = findViewById(R.id.button);
        editTextView = findViewById(R.id.editTextTextPersonName2);
    }
}