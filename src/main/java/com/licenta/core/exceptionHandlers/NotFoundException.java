package com.licenta.core.exceptionHandlers;

import com.licenta.core.enums.ObjectType;

public class NotFoundException extends RuntimeException{

    private static final  String NOT_FOUND = " not found.";

    public NotFoundException(String message) { super(message); }

    public NotFoundException(ObjectType objectType, String name) {

        switch (objectType) {
            case RECIPE -> throw new NotFoundException("Recipe with name " + name + NOT_FOUND);
            case INGREDIENT -> throw new NotFoundException("Ingredient with name " + name + NOT_FOUND);
            case PERSON -> throw new NotFoundException("Person " + name + NOT_FOUND);
            case MEASUREMENT -> throw new NotFoundException("Measurement with name " + name + NOT_FOUND);
            default -> throw new NotFoundException("Not found.");
        }
    }

    public NotFoundException(ObjectType objectType, Long id) {

        switch (objectType) {
            case RECIPE -> throw new NotFoundException("Recipe with id " + id + NOT_FOUND);
            case INGREDIENT -> throw new NotFoundException("Ingredient with id " + id + NOT_FOUND);
            case PERSON -> throw new NotFoundException("Person with id " + id + NOT_FOUND);
            default -> throw new NotFoundException("Not found.");
        }
    }
}
