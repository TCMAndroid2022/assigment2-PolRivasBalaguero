package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Users_Info extends AppCompatActivity {
    ArrayList<User> listUsers;
    UserAdapter adapter;
    RecyclerView recyclerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_info);

            listUsers= (ArrayList<User>) getIntent().getSerializableExtra("newUser");
            adapter = new UserAdapter(listUsers);
            recyclerUser = (RecyclerView) findViewById(R.id.RecyclerId);
            recyclerUser.setLayoutManager(new LinearLayoutManager(this));
            adapter.notifyDataSetChanged();
            recyclerUser.setAdapter(adapter);

    }
}
