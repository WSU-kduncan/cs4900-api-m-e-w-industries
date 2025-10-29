package com.mew.demo.exception;
import java.lang.Exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }
}