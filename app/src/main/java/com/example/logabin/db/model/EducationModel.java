package com.example.logabin.db.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "education_models")
public class EducationModel {
    @NonNull
    @PrimaryKey()
    public String name;
    public int text;
    public int designImg;
    public int tableImg;

    public EducationModel(String name, int text, int designImg, int tableImg){
        this.name = name;
        this.text = text;
        this.designImg = designImg;
        this.tableImg = tableImg;
    }

    @NonNull
    @Override
    public String toString() {
        return "Education{"+
                "name=" + name +
                ", text=" + text +
                "}";
    }
}
