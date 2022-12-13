package com.licenta.core.exceptionHandlers;

import com.licenta.core.enums.ObjectType;

public class NotFoundException extends RuntimeException{

    private static final  String NOT_FOUND = " not found.";

    public NotFoundException(String message) { super(message); }

    public NotFoundException(ObjectType objectType, String name) {

        switch (objectType) {
            case PERSON -> throw new NotFoundException("Person " + name + NOT_FOUND);
            case REVIEW -> throw new NotFoundException("Review " + name + NOT_FOUND);
            default -> throw new NotFoundException("Not found.");
        }
    }

    public NotFoundException(ObjectType objectType, Long id) {

        switch (objectType) {
            case PERSON -> throw new NotFoundException("Person with id " + id + NOT_FOUND);
            case REVIEW -> throw new NotFoundException("Review " + id + NOT_FOUND);
            default -> throw new NotFoundException("Not found.");
        }
    }
}
