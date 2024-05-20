package com.example.logabin.element;


import com.example.logabin.utils.Coordinate;

public class InputChannel extends Element {

    public InputChannel(int id, String name, int outputCount, Coordinate coordinate) {
        super(id, name, 0, outputCount, coordinate);
        setActive(false);
        setOutputCoordinate(coordinate.sum(0, 1));
    }

    @Override
    public void interact() {}
}

