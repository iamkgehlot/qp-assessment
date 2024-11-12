package com.grocery.grocerybooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GroceryItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> hotelNotFoundExceptonHandler(GroceryItemNotFoundException ex){
        ErrorResponse errorResponse=ErrorResponse.builder().httpStatus(HttpStatus.NOT_FOUND).error(ex.getMessage()).build();

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(InvalidInventoryLevelException.class)
    public ResponseEntity<ErrorResponse> InvalidInventoryLevelExceptionHandler(InvalidInventoryLevelException ex){
        ErrorResponse errorResponse=ErrorResponse.builder().httpStatus(HttpStatus.NOT_FOUND).error(ex.getMessage()).build();

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }
}
