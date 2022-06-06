package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
        private EditText UserText;
        private Button addButton;
        private RecyclerView recyclerUsers;
        private UserAdapter UserAdapter;
         Button jugar;
        UserViewModel viewModel;
        String paraula;
        RequestQueue queue;
        String URL = "https://random-word-api.herokuapp.com/word";
        RecyclerView.LayoutManager linear_layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager grid_layoutManager = new GridLayoutManager(this, 2);
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            UserText = findViewById(R.id.User_name);
            addButton = findViewById(R.id.add_button);
            recyclerUsers = findViewById(R.id.collection_view);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerUsers.setLayoutManager(layoutManager);
            UserAdapter = new UserAdapter();


            viewModel = new ViewModelProvider(this).get(UserViewModel.class);

            viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> Users) {

                    UserAdapter.setUsers(Users);
                }
            });

            recyclerUsers.setAdapter(UserAdapter);


            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewModel.insert(UserText.getText().toString());
                }
            });

            queue = Volley.newRequestQueue(this);
            jsonParse();

            jugar = findViewById(R.id.jugar);

            jugar.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent= new Intent(MainActivity.this, Game.class);

                    intent.putExtra("paraula",paraula);
                    intent.putExtra("username",UserText.getText().toString());
                    startActivity(intent);
                }
            });
        }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerUsers.setLayoutManager(linear_layoutManager);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            recyclerUsers.setLayoutManager(grid_layoutManager);
        }
    }
        @Override
        protected void onDestroy() {
            AppDatabase.destroyInstance();
            super.onDestroy();
        }

       @Override
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu,menu);
            return true;
        }

       @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item){
           Intent intent;
            switch (item.getItemId()) {
                case R.id.uno:
                     intent = new Intent(MainActivity.this, TopScores.class);
                    startActivity(intent);
                    break;
            }
            return true;
        }

    private void jsonParse() {


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            paraula = response.toString();
                            Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("TEST", error.getMessage());
                }
            });
            queue.add(jsonObjectRequest);
        }

    }




