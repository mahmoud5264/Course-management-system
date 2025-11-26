package com.example.cms.exceptions;

public class ALreadyExistsException extends RuntimeException {
    public ALreadyExistsException(String message) {
        super(message);
    }
}
