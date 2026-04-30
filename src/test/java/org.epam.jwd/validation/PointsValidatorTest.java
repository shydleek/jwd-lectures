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

    @Test
    public void validate_shouldReturnValidatedListOfBigDecimals() {
        List<Point3d> expectedString = List.of(
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
        List<Point3d> actualString = new ArrayList<>();
        String inputString = "      120     39    130    1849    322  432   324  432   546    ";
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
    public void validateNotNull_shouldThrowValidationException() throws ValidationException, ParseException {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate("");
    }

    @Test(expected = ValidationException.class)
    public void trimAndValidateNotEmpty_shouldThrowValidationException() throws ValidationException, ParseException {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate("     ");
    }

    @Test(expected = ValidationException.class)
    public void validateTokenCount_shouldThrowValidationException() throws ValidationException, ParseException {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate("1 2 3");
    }

    @Test(expected = ParseException.class)
    public void parseTokens_shouldThrowParseException() throws ValidationException, ParseException {
        PointsValidator validator = PointsValidator.getInstance();
        validator.validate("1 2 3.d 4 5 6 7 8 9");
    }
}
