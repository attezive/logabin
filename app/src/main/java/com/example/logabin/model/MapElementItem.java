package com.example.logabin.model;

import androidx.annotation.NonNull;

import com.example.logabin.element.Element;
import com.example.logabin.utils.Coordinate;

public class MapElementItem {
    private int id;
    private int wireType = 0;
    private Element element;
    private int elementPartNumber;
    private Coordinate coordinate;

    public MapElementItem(int id) {
        this.id = id;
        this.coordinate = null;
    }

    public MapElementItem(int id, Coordinate coordinate) {
        this.id = id;
        this.coordinate = coordinate;
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


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public int getWireType() {
        return wireType;
    }

    public void setWireType(int wireType) {
        this.wireType = wireType;
    }
}
