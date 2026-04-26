package org.epam.jwd.exception;

public class PlainNotExistException extends Exception {
    public PlainNotExistException(PlainNotExistException e, String message) {
        super(message);
    }
}
