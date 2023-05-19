package com.restapi.RandomQuestions.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rex, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), rex.getMessage(), req.getDescription(false));
        log.info(errorResponse.getMessage());
        log.error(errorResponse.getDetails());
        return new ResponseEntity<Object>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleCommonException(Exception exception, WebRequest req){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), exception.getMessage(), req.getDescription(false));
        log.info(errorResponse.getMessage());
        log.error(errorResponse.getDetails());
        return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> customValidationException(MethodArgumentNotValidException exception){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), "Validation Error",
                exception.getBindingResult().getFieldError().getDefaultMessage());
        log.info(errorResponse.getMessage());
        log.error(errorResponse.getDetails());
        return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
