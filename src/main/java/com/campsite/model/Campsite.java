package com.campsite.model;

public class Campsite {

    public Campsite() {}

    public Campsite(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
