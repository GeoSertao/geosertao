package edu.ifpb.geosertao.geosertao.core.harvest;

public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String elementName) {
        super("Element not found: " + elementName);
    }

}
