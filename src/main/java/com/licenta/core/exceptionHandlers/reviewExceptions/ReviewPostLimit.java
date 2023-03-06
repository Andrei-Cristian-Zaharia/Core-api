package com.licenta.core.exceptionHandlers.reviewExceptions;

public class ReviewPostLimit extends RuntimeException{

    public ReviewPostLimit() { super("User already posted a review for this recipe and reached maximum limit."); }
}
