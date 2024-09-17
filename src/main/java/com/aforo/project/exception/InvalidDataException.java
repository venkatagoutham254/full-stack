package com.aforo.project.exception;

@SuppressWarnings("serial")
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

