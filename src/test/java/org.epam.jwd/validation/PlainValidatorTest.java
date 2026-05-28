package org.epam.jwd.validation;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class PlainValidatorTest {

    private PlainValidator plainValidator;

    @Before
    public void setUp() {
        plainValidator = PlainValidator.getInstance();
    }

    @Test
    public void isPlaneValid_shouldReturnTrue_whenPointsAreCorrect() {
        Assert.assertTrue(plainValidator.isPlaneValid(initValidPlain()));
    }

    @Test
    public void arePointsValidated_shouldReturnFalse_whenPointsAreIncorrect() {
        Assert.assertFalse(plainValidator.isPlaneValid(initNotValidPlain()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isPlaneValid_shouldThrowIllegalArgumentException_whenIdIsIllegal() {
        plainValidator.isPlaneValid(initPlainWithIllegalId());
    }

    private Plain initPlainWithIllegalId() {
        return new Plain(-1,
                new Point3d(
                        new BigDecimal(14),
                        new BigDecimal(1),
                        new BigDecimal(1)),
                new Point3d(
                        new BigDecimal(1),
                        new BigDecimal(41),
                        new BigDecimal(1)),
                new Point3d(
                        new BigDecimal(1),
                        new BigDecimal(1),
                        new BigDecimal(13)
                ));
    }

    private Plain initValidPlain() {
        return new Plain(1,
                new Point3d(
                        new BigDecimal(14),
                        new BigDecimal(1),
                        new BigDecimal(1)),
                new Point3d(
                        new BigDecimal(1),
                        new BigDecimal(41),
                        new BigDecimal(1)),
                new Point3d(
                        new BigDecimal(1),
                        new BigDecimal(1),
                        new BigDecimal(13)
        ));
    }

    private Plain initNotValidPlain() {
        return new Plain(2,
                new Point3d(
                    new BigDecimal(1),
                    new BigDecimal(1),
                    new BigDecimal(1)),
                new Point3d(
                        new BigDecimal(1),
                        new BigDecimal(1),
                        new BigDecimal(1)),
                new Point3d(
                        new BigDecimal(1),
                        new BigDecimal(1),
                        new BigDecimal(1))
        );
    }
}