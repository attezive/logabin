package com.example.logabin.model;

import androidx.annotation.NonNull;

import com.example.logabin.element.Element;

public class MapElementItem {
    private int id;
    private Element element;
    private int elementPartNumber;

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

    public void setId(int id) {
        this.id = id;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getElementPartNumber() {
        return elementPartNumber;
    }

    public void setElementPartNumber(int elementPartNumber) {
        this.elementPartNumber = elementPartNumber;
    }
}
