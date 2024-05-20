package com.example.logabin.model;

import com.example.logabin.R;
import com.example.logabin.db.model.ElementModel;

import java.util.ArrayList;
import java.util.List;

public class FolderItem {
    private String folderName;
    private int iconPath;

    private List<ElementModel> elements;

    public FolderItem(String folderName, int iconPath, List<ElementModel> elements) {
        this.folderName = folderName;
        this.iconPath = iconPath;
        this.elements = elements;
    }

    public FolderItem(String folderName, int iconPath) {
        this.folderName = folderName;
        this.iconPath = iconPath;
        this.elements = new ArrayList<>();
    }

    public FolderItem(String folderName){
        this.folderName = folderName;
        this.iconPath = R.drawable.folder;
        this.elements = new ArrayList<>();
    }

    public String getFolderName() {
        return folderName;
    }

    public int getIconPath() {
        return iconPath;
    }

    public List<ElementModel> getElements() {
        return elements;
    }

    public void setElements(List<ElementModel> elements) {
        this.elements = elements;
    }

    public void Add(ElementModel element){
        elements.add(element);
    }
}
