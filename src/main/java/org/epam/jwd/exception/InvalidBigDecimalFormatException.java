package org.epam.jwd.exception;

public class InvalidBigDecimalFormatException extends ParseException {
    public InvalidBigDecimalFormatException(InvalidBigDecimalFormatException e, String message) {
        super(message);
    }

    public InvalidBigDecimalFormatException() {
    }
}
