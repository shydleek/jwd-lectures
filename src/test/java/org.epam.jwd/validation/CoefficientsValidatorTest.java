package org.epam.jwd.validation;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CoefficientsValidatorTest {

//    private static final Logger LOG = LoggerFactory.getLogger(CoefficientsValidatorTest.class);
//
//    @Test
//    public void validate_shouldReturnValidatedListOfBigDecimals() {
//        List<BigDecimal> expectedString = List.of(
//                new BigDecimal("14.5"),
//                new BigDecimal("1.0"),
//                new BigDecimal("1.0"),
//                new BigDecimal("13.2")
//        );
//        List<BigDecimal> actualString = new ArrayList<>();
//        String inputString = "   14.5      1.0    1.0     13.2    ";
//        try {
//            CoefficientsValidator validator = CoefficientsValidator.getInstance();
//            actualString = validator.validate(inputString);
//        } catch (ValidationException e) {
//            LOG.error("Validation error, skipped line \"{}\"", inputString);
//        } catch (ParseException e) {
//            LOG.error("Parse error, skipped line \"{}\"", inputString);
//        }
//
//        Assert.assertEquals(expectedString, actualString);
//    }
//
//    @Test(expected = ValidationException.class)
//    public void validateNotNull_shouldThrowValidationException() {
//        CoefficientsValidator validator = CoefficientsValidator.getInstance();
//        validator.validate("");
//    }
//
//    @Test(expected = ValidationException.class)
//    public void trimAndValidateNotEmpty_shouldThrowValidationException() {
//        CoefficientsValidator validator = CoefficientsValidator.getInstance();
//        validator.validate("     ");
//    }
//
//    @Test(expected = ValidationException.class)
//    public void validateTokenCount_shouldThrowValidationException() {
//        CoefficientsValidator validator = CoefficientsValidator.getInstance();
//        validator.validate("1 2 3");
//    }
//
//    @Test(expected = ParseException.class)
//    public void parseTokens_shouldThrowParseException() {
//        CoefficientsValidator validator = CoefficientsValidator.getInstance();
//        validator.validate("1 2 3.d 4");
//    }
}