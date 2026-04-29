package org.epam.jwd.exception;

public class InvalidBigDecimalFormatException extends ParseError {
    public InvalidBigDecimalFormatException(InvalidBigDecimalFormatException e, String message) {
        super(message);
    }

    public InvalidBigDecimalFormatException() {}
}
