/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.geosertao.geosertao.core.harvest;

/**
 *
 * @author Marcelo Augusto
 */
public class AttributeNotFoundException extends Exception{
 
    public AttributeNotFoundException(String attributeName) {
        super("Attribute not found: " + attributeName);
    }
}
