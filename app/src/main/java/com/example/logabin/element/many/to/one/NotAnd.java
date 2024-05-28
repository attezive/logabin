package com.example.logabin.element.many.to.one;

import com.example.logabin.element.Element;
import com.example.logabin.utils.Coordinate;

public class NotAnd extends Element {
    public NotAnd(int id, String name, int inputCount, Coordinate coordinate) {
        super(id, name, inputCount, 1, coordinate);
        setOutputCoordinate(coordinate.sum(inputCount/2, 1));
        int shift = inputCount % 2 == 0 ? 1 : 0;
        for (int i = 0; i < inputCount; i++){
            if (i < inputCount/2){
                setInputCoordinate(coordinate.sum(i, 0));
            } else {
                setInputCoordinate(coordinate.sum(i+shift, 0));
            }
        }
    }

    @Override
    public void interact() {
        for (Element element : getInputElements()){
            if (!element.isActive()){
                setActive(true);
                return;
            }
        }
        setActive(false);
    }
}

