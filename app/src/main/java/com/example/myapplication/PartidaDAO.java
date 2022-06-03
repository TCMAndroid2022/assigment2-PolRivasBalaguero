package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface PartidaDAO {
    @Query("SELECT * FROM  Partida")
    LiveData<List<Partida>> getAll();
    @Query("SELECT POINTS FROM Partida WHERE username LIKE :username")
    String getPoints(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPartida(User element);
}
