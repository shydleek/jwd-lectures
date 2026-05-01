package org.epam.jwd.validation;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PlainValidatorTest {

    private final PlainValidator plainValidator = PlainValidator.getInstance();

    @Test
    public void isPlaneValidated_shouldReturnTrue_whenPointsAreCorrect() {
        Assert.assertTrue(plainValidator.isPlaneValidated(initValidPlain()));
    }

    @Test
    public void arePointsValidated_shouldReturnFalse_whenPointsAreIncorrect() {
        Assert.assertFalse(plainValidator.isPlaneValidated(initNotValidPlain()));
    }

    private Plain initValidPlain() {
        return new Plain(new Point3d(
                new BigDecimal(14),
                new BigDecimal(1),
                new BigDecimal(1)
        ), new Point3d(
                new BigDecimal(1),
                new BigDecimal(41),
                new BigDecimal(1)
        ), new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(13)
        ));
    }

    private Plain initNotValidPlain() {
        return new Plain(new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(1)
        ), new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(1)
        ), new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(1)
        ));
    }
}
