package com.licenta.core.exceptionHandlers.reviewExceptions;

public class ReviewDeleteForbidden extends RuntimeException {

    public ReviewDeleteForbidden() { super("You have to own this review in order to be able to delete it !!!"); }
}
