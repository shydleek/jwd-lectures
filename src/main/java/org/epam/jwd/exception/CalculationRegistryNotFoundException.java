package org.epam.jwd.exception;

public class CalculationRegistryNotFoundException extends RuntimeException{
    public CalculationRegistryNotFoundException(ValidationException e, String message) {
        super(message);
    }

    public CalculationRegistryNotFoundException() {
    }

    public CalculationRegistryNotFoundException(String message) {
        super(message);
    }
}