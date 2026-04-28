package org.epam.jwd.exception;

public class EmptyLineException extends ValidationException {
    public EmptyLineException(EmptyLineException e, String message) {
        super(message);
    }

    public EmptyLineException() {

    }
}
