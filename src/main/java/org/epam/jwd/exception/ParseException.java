package org.epam.jwd.exception;

public class ParseException extends Exception {
    public ParseException(ParseException e, String message) {
        super(message);
    }

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }
}
