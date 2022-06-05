package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopScores  extends AppCompatActivity {
    RecyclerView.LayoutManager linear_layoutManager = new LinearLayoutManager(this);
    RecyclerView.LayoutManager grid_layoutManager = new GridLayoutManager(this, 2);
    UserViewModel viewModel;

    private RecyclerView recyclerUsers;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private TopUsersAdapters TopUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.topscores);
        TopUserAdapter = new TopUsersAdapters();
        recyclerUsers = findViewById(R.id.recyclertopusers);
        recyclerUsers.setLayoutManager(layoutManager);
        recyclerUsers.setAdapter(TopUserAdapter);


        //crea instancia del ViewModel per accedir a les dades del llistat.
        //ViewModel ens permet desvincular la vista (Activity) de la font de dades.
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        //Observe es una funció de LiveData, que ens permet detectar quan
        // les dades s'han modificat.
        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> Users) {
                //onChanged s'executa quan el llistat es modifica a la bbdd.
                //Si afegiu una tasca, veureu que s'executa aquest codi per
                //actualitzar el llistat (adapter)
                TopUserAdapter.setUsers(Users);
            }
        });

        recyclerUsers.setAdapter(TopUserAdapter);




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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent intent;
        switch (item.getItemId()) {
            case R.id.uno:
                intent = new Intent(TopScores.this, TopScores.class);
                startActivity(intent);
                break;
            case R.id.dos:
                intent = new Intent(TopScores.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
