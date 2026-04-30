package org.epam.jwd.validation;

import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PlainValidatorTest {

    private final PlainValidator plainValidator = PlainValidator.getInstance();

    @Test
    public void areCoefficientsValidated_shouldReturnTrue_whenCoefficientsAreCorrect() {
        final BigDecimal aCorrect = BigDecimal.valueOf(3.0);
        final BigDecimal bCorrect = BigDecimal.valueOf(0);
        final BigDecimal cCorrect = BigDecimal.valueOf(0);

        Assert.assertTrue(plainValidator.areCoefficientsValidated(aCorrect, bCorrect, cCorrect));
    }

    @Test
    public void areCoefficientsValidated_shouldReturnFalse_whenCoefficientsAreIncorrect() {
        final BigDecimal aIncorrect = BigDecimal.valueOf(0);
        final BigDecimal bIncorrect = BigDecimal.valueOf(0);
        final BigDecimal cIncorrect = BigDecimal.valueOf(0);

        Assert.assertFalse(plainValidator.areCoefficientsValidated(aIncorrect, bIncorrect, cIncorrect));
    }

    @Test
    public void arePointsValidated_shouldReturnTrue_whenCoefficientsAreCorrect() {
        final Point3d aCorrect = new Point3d(
                new BigDecimal(14),
                new BigDecimal(1),
                new BigDecimal(1)
        );
        final Point3d bCorrect = new Point3d(
                new BigDecimal(1),
                new BigDecimal(41),
                new BigDecimal(1)
        );
        final Point3d cCorrect = new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(13)
        );

        Assert.assertTrue(plainValidator.arePointsValidated(aCorrect, bCorrect, cCorrect));
    }

    @Test
    public void arePointsValidated_shouldReturnFalse_whenCoefficientsAreIncorrect() {
        final Point3d aIncorrect = new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(1)
        );
        final Point3d bIncorrect = new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(1)
        );
        final Point3d cIncorrect = new Point3d(
                new BigDecimal(1),
                new BigDecimal(1),
                new BigDecimal(1)
        );

        Assert.assertFalse(plainValidator.arePointsValidated(aIncorrect, bIncorrect, cIncorrect));
    }
}
