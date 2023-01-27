package com.licenta.core;

import com.licenta.core.exceptionHandlers.AlreadyExistsException;
import com.licenta.core.exceptionHandlers.NotFoundException;
import com.licenta.core.exceptionHandlers.authExceptios.FailedAuth;
import com.licenta.core.exceptionHandlers.authExceptios.PersonAlreadyExists;
import com.licenta.core.exceptionHandlers.reviewExceptions.ReviewDeleteForbidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PersonAlreadyExists.class, AlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyExists(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({FailedAuth.class, ReviewDeleteForbidden.class})
    public ResponseEntity<Object> handleFailedAuth(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
