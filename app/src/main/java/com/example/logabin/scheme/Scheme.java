package com.example.logabin.scheme;

import com.example.logabin.element.Element;

import java.util.ArrayList;
import java.util.List;

public class Scheme{
    private List<Element> elements;
    private int idsCount;

    public Scheme(){
        elements = new ArrayList<>();
        idsCount = 0;
    }

    public Scheme(List<Element> elements){
        this.elements = elements;
    }

    public boolean add(Element element){
        if (elements.contains(element))
            return false;
        idsCount++;
        return elements.add(element);
    }

    public Element get(int index){
        return elements.get(index);
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public Element getByID(int id){
        for (Element element : elements){
            if (element.getId() == id)
                return element;
        }
        return null;
    }

    public int getIdsCount() {
        return idsCount;
    }

    public int generateId(){
        return idsCount+1;
    }
}

