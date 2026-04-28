package org.epam.jwd.exception;

public class ParseError extends Exception {
    public ParseError(ParseError e, String message) {
        super(message);
    }

    public ParseError() {
    }

    public ParseError(String message) {
        super(message);
    }
}
