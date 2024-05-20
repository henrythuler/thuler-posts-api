package com.thuler.socialmedia.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest req){

        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Object not found...";
        String message = e.getMessage();
        StandardError err = new StandardError(Instant.now(), status.value(), error, message, req.getRequestURI());
        return ResponseEntity.status(status).body(err);

    }

}
