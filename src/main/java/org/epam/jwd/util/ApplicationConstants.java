package org.epam.jwd.util;

public enum ApplicationConstants {
    PROG_START("Program start!"), PROG_END("Program end!"),
    ERR_PLAIN("Plain can not be composed!"),
    ERR_PLAIN_COEFFS("Plain does not exists (a^2 + b^2 + c^2 > 0)"),
    ERR_PLAIN_POINTS("Plain does not exists (check coordinates of the points)"),
    PATH_TO_CORRECT_FILE("src/test/resources/inputCorrectCoefficient.txt"),
    PATH_TO_INCORRECT_FILE("src/test/resources/inputIncorrectCoefficient.txt"),
    ;

    private final String value;

    ApplicationConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
