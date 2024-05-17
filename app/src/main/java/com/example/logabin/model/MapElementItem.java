package com.example.logabin.model;

import androidx.annotation.NonNull;

public class MapElementItem {
    private int id;

    public MapElementItem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
