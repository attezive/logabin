package com.example.logabin.element;


import com.example.logabin.utils.Coordinate;

public class OutputChannel extends Element{
    public OutputChannel(int id, String name, int inputCount, Coordinate coordinate) {
        super(id, name, inputCount, 0, coordinate);
        setInputCoordinate(coordinate);
    }

    @Override
    public void interact() {
        setActive(getInputElements().get(0).isActive());
    }
}
