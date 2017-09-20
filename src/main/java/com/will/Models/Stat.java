package com.will.Models;

public class Stat {
    private int id;
    private String name;
    private int wins;
    private int losses;

    public Stat(int id, String name, int wins, int losses) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }
}
