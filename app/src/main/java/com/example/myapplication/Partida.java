package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Partida {

    public Partida() {
    }

    public Partida(String user, String points) {
        this.user=user;
        this.points=points;
    }


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String user;

    @ColumnInfo(name = "points")
    public String points;


}
