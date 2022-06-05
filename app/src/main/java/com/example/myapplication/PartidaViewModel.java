package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

    public class PartidaViewModel extends AndroidViewModel {
        private DatabaseController repository; //Instancia al controller
        private LiveData<List<Partida>> allPartidas; //LiveData

        public PartidaViewModel(@NonNull Application application) {
            super(application);
            repository = new DatabaseController(application);
            allPartidas = repository.fetch();
        }

        LiveData<List<Partida>> getAllPartidas() {
            return allPartidas;
        }

        void insert(String name,String points) {
            repository.setPartida(name,points);
        }
    }




