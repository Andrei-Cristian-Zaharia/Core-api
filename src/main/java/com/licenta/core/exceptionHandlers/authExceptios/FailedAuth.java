package com.licenta.core.exceptionHandlers.authExceptios;

public class FailedAuth extends RuntimeException {

    public FailedAuth() { super("Invalid username or password !"); }
}
