package com.example.logabin.model;

import com.example.logabin.R;

public class FolderItem {
    private String folderName;
    private int iconPath;

    public FolderItem(String folderName, int iconPath) {
        this.folderName = folderName;
        this.iconPath = iconPath;
    }

    public FolderItem(String folderName){
        this.folderName = folderName;
        this.iconPath = R.drawable.folder;
    }

    public String getFolderName() {
        return folderName;
    }

    public int getIconPath() {
        return iconPath;
    }
}
