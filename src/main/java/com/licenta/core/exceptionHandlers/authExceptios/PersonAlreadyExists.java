package com.licenta.core.exceptionHandlers.authExceptios;

public class PersonAlreadyExists extends RuntimeException {

    public PersonAlreadyExists() { super("This email has been already used !"); }
}
