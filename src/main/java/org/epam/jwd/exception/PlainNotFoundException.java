package org.epam.jwd.exception;

public class PlainNotFoundException extends RuntimeException {
    public PlainNotFoundException(ValidationException e, String message) {
        super(message);
    }

    public PlainNotFoundException() {
    }
}