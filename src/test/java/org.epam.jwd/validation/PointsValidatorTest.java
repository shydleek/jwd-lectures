package org.epam.jwd.validation;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PointsValidatorTest {

    private static final Logger LOG = LoggerFactory.getLogger(PointsValidatorTest.class);
    private final String notValidatedString = "      120     39    130    1849    322  432   324  432   546    ";
    private final String emptyString = "";
    private final String whitespacedString = "     ";
    private final String incompleteString = "1 2 3";
    private final String invalidString = "1 2 3.d 4 5 6 7 8 9";

    @Test
    public void validate_shouldReturnValidatedListOfPoints_whenPointsAreValid() {
        List<Point3d> expectedString = initList();
        List<Point3d> actualString = new ArrayList<>();
        String inputString = notValidatedString;
        try {
            PointsValidator validator = PointsValidator.getInstance();
            actualString = validator.validate(inputString);
        } catch (ValidationException e) {
            LOG.error("Validation error, skipped line \"{}\"", inputString);
        } catch (ParseException e) {
            LOG.error("Parse error, skipped line \"{}\"", inputString);
        }

        Assert.assertEquals(expectedString, actualString);
    }

    @Test(expected = ValidationException.class)
    public void validateNotNull_shouldThrowValidationException_whenStringIsEmpty() {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate(emptyString);
    }

    @Test(expected = ValidationException.class)
    public void trimAndValidateNotEmpty_shouldThrowValidationException_whenStringIsOnlyWhitespaced() {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate(whitespacedString);
    }

    @Test(expected = ValidationException.class)
    public void validateTokenCount_shouldThrowValidationException_whenStringIsIncomplete() {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate(incompleteString);
    }

    @Test(expected = ParseException.class)
    public void parseTokens_shouldThrowParseException_whenStringIsNotParsedIntoBigDecimals() {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate(invalidString);
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
