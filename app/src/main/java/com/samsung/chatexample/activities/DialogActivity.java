package com.samsung.chatexample.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.samsung.chatexample.R;

public class DialogActivity extends AppCompatActivity {
    RecyclerView chatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initViews();

    }

    public void initViews() {
        chatView = findViewById()
    }
}