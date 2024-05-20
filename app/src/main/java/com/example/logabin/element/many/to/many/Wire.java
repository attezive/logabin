package com.example.logabin.element.many.to.many;

import com.example.logabin.element.Element;
import com.example.logabin.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Wire extends Element {
    private boolean onceActivated = false;
    private boolean wasChanged = false;

    public Wire(int id, String name, int inputCount, int outputCount, Coordinate coordinate, Coordinate endCoordinate) {
        super(id, name, inputCount, outputCount, coordinate);
        setInputCoordinate(coordinate);
        setOutputCoordinate(endCoordinate);
    }

    @Override
    public void setActive(boolean active) {
        if (onceActivated && isActive()!=active){
            super.setActive(active);
            wasChanged = true;
            return;
        }
        if (!onceActivated) {
            onceActivated = true;
            super.setActive(active);
            wasChanged = true;
            return;
        }
        wasChanged = false;
    }

    @Override
    public List<Element> getOutputElements() {
        if (!wasChanged) return new ArrayList<>();
        return super.getOutputElements();
    }

    @Override
    public void interact() {
        for (Element element : getInputElements()){
            if (element.isActive()){
                setActive(true);
                return;
            }
        }
        setActive(false);
    }

    public void interact_back(){
        for (Element element : getOutputElements()){
            if (element.getName().equals("Wire") && element.isActive()){
                setActive(true);
                return;
            }
        }
    }


}

