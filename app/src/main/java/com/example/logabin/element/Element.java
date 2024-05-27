package com.example.logabin.element;

import com.example.logabin.utils.Coordinate;
import com.example.logabin.utils.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class Element {
    private boolean isActive;
    private int id;
    private Coordinate coordinate;
    private Direction direction;
    private String name;
    private String mark;
    private int inputCount;
    private int outputCount;
    private List<Element> inputElements;
    private List<Element> outputElements;
    private List<Coordinate> inputCoordinates;
    private List<Coordinate> outputCoordinates;

    public Element(int id, String name, int inputCount, int outputCount, Coordinate coordinate){
        this.id = id;
        this.name = name;
        this.inputCount = inputCount;
        this.outputCount = outputCount;
        this.coordinate = coordinate;
        inputElements = new ArrayList<>();
        outputElements = new ArrayList<>();
        inputCoordinates = new ArrayList<>();
        outputCoordinates = new ArrayList<>();
    }

    public boolean isActive() {
        return isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMark() {
        return mark;
    }

    public int getInputCount() {
        return inputCount;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }

    public void setOutputCount(int outputCount) {
        this.outputCount = outputCount;
    }

    public List<Element> getInputElements() {
        return inputElements;
    }

    public List<Element> getOutputElements() {
        return outputElements;
    }

    public void setInputElement(Element inputWire) {
        if (!inputElements.contains(inputWire))
            this.inputElements.add(inputWire);
    }

    public void setOutputElement(Element outputWire) {
        if (!outputElements.contains(outputWire))
            this.outputElements.add(outputWire);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    abstract public void interact();

    public List<Coordinate> getInputCoordinates() {
        return inputCoordinates;
    }

    public List<Coordinate> getOutputCoordinates() {
        return outputCoordinates;
    }

    public void setInputCoordinate(Coordinate coordinate) {
        if (!inputCoordinates.contains(coordinate))
            this.inputCoordinates.add(coordinate);
    }

    public void setOutputCoordinate(Coordinate coordinate) {
        if (!outputCoordinates.contains(coordinate))
            this.outputCoordinates.add(coordinate);
    }

    public void setOutputCoordinates(List<Coordinate> coordinates){
        this.outputCoordinates = coordinates;
    }
}
