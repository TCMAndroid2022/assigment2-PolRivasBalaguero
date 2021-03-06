package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface PartidaDAO {
    @Query("SELECT * FROM  Partida")
    LiveData<List<Partida>> getAll();
    @Query("SELECT * FROM Partida WHERE username LIKE :username")
    LiveData<List<Partida>> getPartidasUsername(String username);
    @Query("SELECT MAX(POINTS)FROM Partida WHERE username IS :username")
    String getMaxPoints(String username);
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertPartida(Partida element);
}
