package com.samsung.chatexample.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.samsung.chatexample.R;
import com.samsung.chatexample.adapters.MessageAdapter;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.services.ChatService;

public class DialogActivity extends AppCompatActivity {
    RecyclerView chatView;
    Button sendView;
    EditText editTextView;

    User currentUser;
    User toUser;

    MessageAdapter adapter;

    OnFailureListener failureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error happend!", Toast.LENGTH_SHORT).show();
        }
    };

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

                if (adapter.getItemCount() == 0) {
                    ChatService.createDialog(currentUser, toUser)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    ChatService.sendMessage(text, currentUser, toUser)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    editTextView.getText().clear();
                                                }
                                            })
                                            .addOnFailureListener(failureListener);
                                }
                            }).addOnFailureListener(failureListener);
                }
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

        adapter = new MessageAdapter(ChatService.getUserOptions(currentUser, toUser));
        adapter.startListening();

        chatView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        chatView.setLayoutManager(manager);
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