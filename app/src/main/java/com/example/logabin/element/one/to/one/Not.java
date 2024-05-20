package com.example.logabin.element.one.to.one;

import com.example.logabin.element.Element;
import com.example.logabin.utils.Coordinate;

public class Not extends Element {

    public Not(int id, String name, Coordinate coordinate) {
        super(id, name, 1, 1, coordinate);
        setInputCoordinate(coordinate);
        setOutputCoordinate(coordinate.sum(0, 1));
    }

    @Override
    public void interact() {
        setActive(!getInputElements().get(0).isActive());
    }
}
