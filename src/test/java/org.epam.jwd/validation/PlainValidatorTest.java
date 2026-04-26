package org.epam.jwd.validation;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class PlainValidatorTest {
    private static final Logger LOG = LoggerFactory.getLogger(PlainValidatorTest.class);
// TODO: finish with this test
//    @Test
//    public void isMonic_shouldReturn() {
//        PlainValidator validator = new PlainValidator();
//        List<BigDecimal> list = new ArrayList<>();
//        list.add(BigDecimal.valueOf(2.0));
//        list.add(BigDecimal.valueOf(4.0));
//        list.add(BigDecimal.valueOf(6.0));
//        list.add(BigDecimal.valueOf(8.0));
//        LOG.info(list.toString());
//        validator.isMonic(list);
//
//        Assert.assertEquals(A, list.get(0));
//        Assert.assertEquals(B, list.get(1));
//        Assert.assertEquals(C, list.get(2));
//        Assert.assertEquals(D, list.get(3));
//    }

    @Test
    public void areCoefficientsValidated_shouldReturnTrue_whenCoefficientsAreCorrect() {
        final BigDecimal aCorrect = BigDecimal.valueOf(3.0);
        final BigDecimal bCorrect = BigDecimal.valueOf(0);
        final BigDecimal cCorrect = BigDecimal.valueOf(0);
        PlainValidator validator = new PlainValidator();

        Assert.assertTrue(validator.areCoefficientsValidated(aCorrect, bCorrect, cCorrect));
    }

    @Test
    public void areCoefficientsValidated_shouldReturnFalse_whenCoefficientsAreIncorrect() {
        final BigDecimal aIncorrect = BigDecimal.valueOf(0);
        final BigDecimal bIncorrect = BigDecimal.valueOf(0);
        final BigDecimal cIncorrect = BigDecimal.valueOf(0);
        PlainValidator validator = new PlainValidator();

        Assert.assertFalse(validator.areCoefficientsValidated(aIncorrect, bIncorrect, cIncorrect));
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
        PlainValidator validator = new PlainValidator();

        Plain plain = new Plain(aCorrect, bCorrect, cCorrect);
        LOG.info("A = {}", plain.getA());
        LOG.info("B = {}", plain.getB());
        LOG.info("C = {}", plain.getC());
        LOG.info("D = {}", plain.getD());
        Assert.assertTrue(validator.arePointsValidated(aCorrect, bCorrect, cCorrect));
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
        PlainValidator validator = new PlainValidator();

        Plain plain = new Plain(aIncorrect, bIncorrect, cIncorrect);
        LOG.info("A = {}", plain.getA());
        LOG.info("B = {}", plain.getB());
        LOG.info("C = {}", plain.getC());
        LOG.info("D = {}", plain.getD());
        Assert.assertFalse(validator.arePointsValidated(aIncorrect, bIncorrect, cIncorrect));
    }
}
