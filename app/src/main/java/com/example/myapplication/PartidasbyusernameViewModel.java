package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PartidasbyusernameViewModel extends AndroidViewModel {
    private DatabaseController repository;

    public PartidasbyusernameViewModel(@NonNull Application application) {
        super(application);
        repository = new DatabaseController(application);
    }

    LiveData<List<Partida>> getPartidasbyusername(String username) {
        return repository.getAllPartidabyusername(username);
    }

    void insert(String name, String points) {
        repository.setPartida(name, points);
    }
}

