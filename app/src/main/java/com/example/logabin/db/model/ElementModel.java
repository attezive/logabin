package com.example.logabin.db.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "element_models")
public class ElementModel {
    @NonNull
    @PrimaryKey()
    public String name;
    public int inputCount;
    public int outputCount;
    public int icon;

    public ElementModel(String name, int inputCount, int outputCount, int icon){
        this.name = name;
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.icon = icon;
    }

    @NonNull
    @Override
    public String toString() {
        return "Element{"+
                "name=" + name +
                ", inputCount=" + inputCount +
                ", outputCount=" + outputCount +
                "}";
    }
}
