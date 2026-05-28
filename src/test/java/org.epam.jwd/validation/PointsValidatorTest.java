package org.epam.jwd.validation;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class PointsValidatorTest {

    private final String notValidatedString = "      120     39    130    1849    322  432   324  432   546    ";
    private final String emptyString = "";
    private final String whitespacedString = "     ";
    private final String incompleteString = "1 2 3";
    private final String invalidString = "1 2 3.d 4 5 6 7 8 9";

    private PointsValidator pointsValidator;

    @Before
    public void setUp() {
        pointsValidator = PointsValidator.getInstance();
    }

    @Test
    public void convert_shouldReturnValidatedListOfPoints_whenPointsAreValid() {
        List<Point3d> expectedString = initList();
        List<Point3d> actualString = pointsValidator.convert(notValidatedString);

        Assert.assertEquals(expectedString, actualString);
    }

    @Test(expected = ValidationException.class)
    public void validateNotNull_shouldThrowValidationException_whenStringIsEmpty() {
        pointsValidator.convert(emptyString);
    }

    @Test(expected = ValidationException.class)
    public void trimAndValidateNotEmpty_shouldThrowValidationException_whenStringIsOnlyWhitespaced() {
        pointsValidator.convert(whitespacedString);
    }

    @Test(expected = ValidationException.class)
    public void validateTokenCount_shouldThrowValidationException_whenStringIsIncomplete() {
        pointsValidator.convert(incompleteString);
    }

    @Test(expected = ParseException.class)
    public void parseTokens_shouldThrowParseException_whenStringIsNotParsedIntoBigDecimals() {
        pointsValidator.convert(invalidString);
    }

    private List<Point3d> initList() {
        return List.of(
                new Point3d(
                        new BigDecimal(120),
                        new BigDecimal(39),
                        new BigDecimal(130)
                ),
                new Point3d(
                        new BigDecimal(1849),
                        new BigDecimal(322),
                        new BigDecimal(432)
                ),
                new Point3d(
                        new BigDecimal(324),
                        new BigDecimal(432),
                        new BigDecimal(546)
                )
        );
    }
}