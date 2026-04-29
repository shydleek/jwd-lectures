package org.epam.jwd.validation;

import org.epam.jwd.exception.ParseError;
import org.epam.jwd.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CoefficientsValidatorTest {

    private static final Logger LOG = LoggerFactory.getLogger(CoefficientsValidatorTest.class);

    @Test
    public void validate_shouldReturnValidatedListOfBigDecimals() {
        List<BigDecimal> expectedString = List.of(
                new BigDecimal("14.5"),
                new BigDecimal("1.0"),
                new BigDecimal("1.0"),
                new BigDecimal("13.2")
        );
        List<BigDecimal> actualString = new ArrayList<>();
        String inputString = "   14.5      1.0    1.0     13.2    ";
        try {
            CoefficientsValidator validator = new CoefficientsValidator(inputString);
            actualString = validator.validate();
        } catch (ValidationException e) {
            LOG.error("Validation error, skipped line \"{}\"", inputString);
        } catch (ParseError e) {
            LOG.error("Parse error, skipped line \"{}\"", inputString);
        }

        Assert.assertEquals(expectedString, actualString);
    }

    @Test(expected = ValidationException.class)
    public void validateNotNull_shouldThrowValidationException() throws ValidationException, ParseError {
        CoefficientsValidator validator = new CoefficientsValidator("");
        validator.validate();
    }

    @Test(expected = ValidationException.class)
    public void trimAndValidateNotEmpty_shouldThrowValidationException() throws ValidationException, ParseError {
        CoefficientsValidator validator = new CoefficientsValidator("     ");
        validator.validate();
    }

    @Test(expected = ValidationException.class)
    public void validateTokenCount_shouldThrowValidationException() throws ValidationException, ParseError {
        CoefficientsValidator validator = new CoefficientsValidator("1 2 3");
        validator.validate();
    }

    @Test(expected = ParseError.class)
    public void parseTokens_shouldThrowParseException() throws ValidationException, ParseError {
        CoefficientsValidator validator = new CoefficientsValidator("1 2 3.d 4");
        validator.validate();
    }
}