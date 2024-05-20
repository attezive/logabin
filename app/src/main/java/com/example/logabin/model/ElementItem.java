package com.example.logabin.model;

import com.example.logabin.R;

public class ElementItem {
    private String elementName;
    private int icon;


    public ElementItem(String elementName, int icon) {
        this.elementName = elementName;
        this.icon = icon;
    }

    public ElementItem(String elementName) {
        this.elementName = elementName;
        this.icon = R.drawable.schemes;
    }

    public String getElementName() {
        return elementName;
    }

    public int getIcon() {
        return icon;
    }
}
