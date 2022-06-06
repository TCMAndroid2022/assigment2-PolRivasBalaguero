package com.example.myapplication;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
public class Allgamesbyuser extends AppCompatActivity {
    PartidasbyusernameViewModel viewModel;
    private RecyclerView recyclerPartidas;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private AllgamesbyuserAdapter AllgamesbyuserAdapter;
    RecyclerView.LayoutManager linear_layoutManager = new LinearLayoutManager(this);
    RecyclerView.LayoutManager grid_layoutManager = new GridLayoutManager(this, 2);
    String username;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.topscores);
        recyclerPartidas = findViewById(R.id.recyclertopsers);
        recyclerPartidas.setLayoutManager(layoutManager);
        username=getIntent().getStringExtra("username");

        AllgamesbyuserAdapter = new AllgamesbyuserAdapter(this::onItemClick);

        viewModel = new ViewModelProvider(this).get(PartidasbyusernameViewModel.class);


        viewModel.getPartidasbyusername(username).observe(this, new Observer<List<Partida>>() {
            @Override
            public void onChanged(List<Partida> partidas) {


                AllgamesbyuserAdapter.setPartidas(partidas);
            }
        });

        recyclerPartidas.setAdapter(AllgamesbyuserAdapter);


    }

    public void onItemClick(int position) {

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
                intent = new Intent(Allgamesbyuser.this, TopScores.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
