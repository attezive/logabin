package com.example.logabin.element.many.to.one;

import com.example.logabin.element.Element;
import com.example.logabin.utils.Coordinate;

public class Xor extends Element {
    public Xor(int id, String name, int inputCount, Coordinate coordinate) {
        super(id, name, inputCount, 1, coordinate);
        setOutputCoordinate(coordinate.sum(inputCount/2, 2));
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
        boolean hasAnyActive = false;
        boolean hasAllActive = true;
        for (Element element : getInputElements()){
            if (element.isActive() && !hasAnyActive){
                hasAnyActive = true;
            }
            else if (!element.isActive() && hasAllActive){
                hasAllActive = false;
            }
        }
        setActive(hasAnyActive && !hasAllActive);
    }
}

