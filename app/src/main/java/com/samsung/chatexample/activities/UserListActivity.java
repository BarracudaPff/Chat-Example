package com.samsung.chatexample.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.samsung.chatexample.R;
import com.samsung.chatexample.adapters.UsersAdapter;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.models.application.User;
import com.samsung.chatexample.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ArrayList<User> users = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.userListView);
        UsersAdapter adapter = new UsersAdapter(users, new MyValueEventListener<User>() {
            @Override
            public void onValue(User user) {
                Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
                intent.putExtra("DIALOG_WITH", user);
                intent.putExtra("DIALOG_FROM", (User) getIntent().getSerializableExtra("DIALOG_FROM"));
                startActivity(intent);
                finish();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        DatabaseService.getUsers(new MyValueEventListener<List<User>>() {
            @Override
            public void onValue(List<User> userList) {
                users.addAll(userList);
                adapter.notifyDataSetChanged();
            }
        });
    }
}