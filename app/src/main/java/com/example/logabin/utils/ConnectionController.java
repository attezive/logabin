package com.example.logabin.utils;

import com.example.logabin.element.Element;
import com.example.logabin.scheme.Scheme;


public class ConnectionController {
    private static ConnectionController _instance;
    public ConnectionController(){
        if (_instance == null){
            _instance = this;
        }
    }

    public static ConnectionController instance(){
        if (_instance == null){
            _instance = new ConnectionController();
        }
        return _instance;
    }

    public void updateAll(Scheme elements){
        for (Element element : elements.getElements()){
            for (Element subElement : elements.getElements()){
                if (element == subElement) continue;
                for (Coordinate coordinate: element.getOutputCoordinates()){
                    for (Coordinate subCoordinate: subElement.getInputCoordinates()){
                        if (coordinate.equals(subCoordinate)){
                            updateElements(element, subElement);
                        }
                    }
                }
            }
        }
    }

    public void updateElements(Element elementFirst, Element elementSecond){
        elementFirst.setOutputElement(elementSecond);
        elementSecond.setInputElement(elementFirst);
    }
}

