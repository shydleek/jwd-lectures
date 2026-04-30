package org.epam.jwd.exception;

public class InvalidCoefficientsCountException extends ValidationException {
    public InvalidCoefficientsCountException(InvalidCoefficientsCountException e, String message) {
        super(message);
    }

    public InvalidCoefficientsCountException() {
    }
}
