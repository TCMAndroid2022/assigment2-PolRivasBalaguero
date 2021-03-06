package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TopScores  extends AppCompatActivity {
    RecyclerView.LayoutManager linear_layoutManager = new LinearLayoutManager(this);
    RecyclerView.LayoutManager grid_layoutManager = new GridLayoutManager(this, 2);
    PartidaViewModel viewModel;
    private RecyclerView recyclerPartidas;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private TopUsersAdapters TopUserAdapter;
    List<Partida> listapartidas= new ArrayList<Partida>();;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.topscores);
        recyclerPartidas = findViewById(R.id.recyclertopsers);
        recyclerPartidas.setLayoutManager(layoutManager);


        TopUserAdapter = new TopUsersAdapters(this::onItemClick);

        viewModel = new ViewModelProvider(this).get(PartidaViewModel.class);


        viewModel.getAllPartidas().observe(this, new Observer<List<Partida>>() {
            @Override
            public void onChanged(List<Partida> partidas) {
                TopUserAdapter.setPartidas(partidas);
            }
        });



        recyclerPartidas.setAdapter(TopUserAdapter);

        TopUserAdapter.setOnClicListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(TopScores.this, Allgamesbyuser.class);
                TextView tv_name_topology = (TextView) v.findViewById(R.id.Nombre);
                String name_topology = tv_name_topology.getText().toString();

                intent.putExtra("username",name_topology);
                startActivity(intent);


            }});





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


    public void onItemClick(int position) {
        listapartidas=TopUserAdapter.getpartidas();
        Intent intent = new Intent(TopScores.this, Allgamesbyuser.class);

        intent.putExtra("username",listapartidas.get(position).user);

        startActivity(intent);
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
