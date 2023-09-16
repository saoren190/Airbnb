package com.laioffer.staybooking.exception;

public class StayNotExistException extends RuntimeException{
    public StayNotExistException(String message) {
        super(message);
    }
}
