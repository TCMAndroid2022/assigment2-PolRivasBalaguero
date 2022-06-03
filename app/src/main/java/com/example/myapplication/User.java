package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.UUID;

@Entity
public class User {
    private  String name;
    private ArrayList<Partida> Npartides=new ArrayList<Partida>();
    public User() {
    }

    public void addPartida(Partida partida){
        Npartides.add(partida);
    }
    public int getNumeroPartides(){
        return Npartides.size();
    }
    public  int getTotalPoints(){
        int points=0;
        for(Partida p: Npartides){
            points+=p.getPoints();
        }
        return points;
    }


    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task")
    public String task;
}
