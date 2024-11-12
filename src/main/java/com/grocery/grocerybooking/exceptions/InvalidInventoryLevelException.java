package com.grocery.grocerybooking.exceptions;

public class InvalidInventoryLevelException extends RuntimeException{
    public InvalidInventoryLevelException(String message){
        super(message);
    }
}
