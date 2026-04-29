package org.epam.jwd.exception;

public class NullLineException extends ValidationException {
    public NullLineException(NullLineException e, String message) {
        super(message);
    }

    public NullLineException() {}
}
