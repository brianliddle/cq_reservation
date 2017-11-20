package com.campsite.model;

/**
 * Campsite pojo.
 *
 * TODO: implement Comparable to aid in sorting and retrieving.
 */
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
