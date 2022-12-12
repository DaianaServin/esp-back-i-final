package com.dh.catalog.exceptions;

public class CircuitBreak extends Exception{
    public CircuitBreak(String message) {
        super(message);
    }
}
