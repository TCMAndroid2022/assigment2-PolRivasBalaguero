package com.example.myapplication;

public class Partida {
    private String id;
    private int points;
    private String user;

    public Partida(String id, int points, String user) {
        this.id = id;
        this.points = points;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
