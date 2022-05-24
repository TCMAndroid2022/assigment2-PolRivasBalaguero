package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MainActivity extends AppCompatActivity {
        private EditText UserText;
        private Button addButton;
        private RecyclerView collectionView;
        private UserAdapter UserAdapter;
        UserViewModel viewModel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            UserText = findViewById(R.id.User_name);
            addButton = findViewById(R.id.add_button);
            collectionView = findViewById(R.id.collection_view);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            collectionView.setLayoutManager(layoutManager);

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
                    UserAdapter.setUsers(Users);
                }
            });

            UserAdapter = new UserAdapter();
            collectionView.setAdapter(UserAdapter);


            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Afegim a través del viewmodel
                    viewModel.insert(UserText.getText().toString());
                    UserText.setText("");
                }
            });
        }

        @Override
        protected void onDestroy() {
            AppDatabase.destroyInstance();
            super.onDestroy();
        }
    }
