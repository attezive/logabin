package com.example.logabin.utils;

import com.example.logabin.element.Element;
import com.example.logabin.scheme.Scheme;

import java.util.ArrayList;
import java.util.List;

public class InteractionController {
    private static InteractionController _instance;
    public InteractionController(){
        if (_instance == null){
            _instance = this;
        }
    }

    public static InteractionController instance(){
        if (_instance == null){
            _instance = new InteractionController();
        }
        return _instance;
    }

    public void updateInteractions(Scheme scheme){
        updateInteractions(scheme, "InputChannel");
    }

    public void updateInteractions(Scheme scheme, String name){
        List<Integer> ids = new ArrayList<>();
        for (Element element : scheme.getElements()){
            if (element.getName().equals(name)){
                ids.add(element.getId());
            }
        }
        updateInteractions(scheme, ids);
    }

    public void updateInteractions(Scheme scheme, Element initialElement){
        for (Element element : scheme.getElements()){
            if (element == initialElement){
                updateInteractions(scheme, element.getId());
                break;
            }
        }
    }

    public void updateInteractions(Scheme scheme, List<Integer> ids){
        for (Integer id: ids){
            updateInteractions(scheme, id);
        }
    }

    public void updateInteractions(Scheme scheme, int id){
        Element currentElement = scheme.getByID(id);
        interact(currentElement);
    }

    private void interact(Element element) {
        element.interact();
        for (Element nextElement : element.getOutputElements()) {
            interact(nextElement);
        }
    }
}

