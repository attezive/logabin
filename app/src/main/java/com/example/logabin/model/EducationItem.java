package com.example.logabin.model;

import com.example.logabin.db.model.EducationModel;

public class EducationItem {
    private String name;
    private int text;
    private int designImg;
    private int tableImg;

    public EducationItem(String name, int text, int designImg, int tableImg) {
        this.name = name;
        this.text = text;
        this.designImg = designImg;
        this.tableImg = tableImg;
    }

    public EducationItem(EducationModel model) {
        this.name = model.name;
        this.text = model.text;
        this.designImg = model.designImg;
        this.tableImg = model.tableImg;
    }

    public String getName() {
        return name;
    }

    public int getText() {
        return text;
    }

    public int getDesignImg() {
        return designImg;
    }

    public int getTableImg() {
        return tableImg;
    }
}
