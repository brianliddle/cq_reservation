package com.campsite.model;

public class Gaprule {

    private int id;
    private int gapSize;

    public Gaprule() {}
    public Gaprule(int id, int gapSize) {
        this.id=id;
        this.gapSize=gapSize;
    }

    public int getId() {
        return id;
    }

    public int getGapSize() {
        return gapSize;
    }
}
