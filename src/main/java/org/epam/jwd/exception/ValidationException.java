package org.epam.jwd.exception;

public class ValidationException extends Exception {
    public ValidationException(ValidationException e, String message) {
        super(message);
    }

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
