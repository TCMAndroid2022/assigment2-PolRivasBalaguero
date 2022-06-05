package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopScores  extends AppCompatActivity {
    RecyclerView.LayoutManager linear_layoutManager = new LinearLayoutManager(this);
    RecyclerView.LayoutManager grid_layoutManager = new GridLayoutManager(this, 2);
    PartidaViewModel viewModel;
    LiveData<List<Partida>> partidas;
    private RecyclerView recyclerPartidas;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private TopUsersAdapters TopUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.topscores);
        TopUserAdapter = new TopUsersAdapters();
        recyclerPartidas = findViewById(R.id.recyclertopsers);
        recyclerPartidas.setLayoutManager(layoutManager);
        recyclerPartidas.setAdapter(TopUserAdapter);



        viewModel = new ViewModelProvider(this).get(PartidaViewModel.class);

        partidas=viewModel.getAllPartidas();
      

        String user,points;
        for (int x=0;x<partidas.getValue().size();x++){
           user=  partidas.getValue().get(x).user;
           points=  partidas.getValue().get(x).user;

        }

        TopUserAdapter.setPartidas(partidas);


        recyclerPartidas.setAdapter(TopUserAdapter);




    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            recyclerPartidas.setLayoutManager(linear_layoutManager);
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            recyclerPartidas.setLayoutManager(grid_layoutManager);
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

            case R.id.dos:
                intent = new Intent(TopScores.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
