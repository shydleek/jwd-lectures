package org.epam.jwd.math;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PlainCalculatorTest {

    private final PlainCalculator plainCalculator = PlainCalculator.getInstance();

    @Test
    public void angleToXAxis_shouldReturnCorrectValue_whenPlainIsFromCoefficients() {
        final BigDecimal angleExpected = new BigDecimal("0.61548");
        final BigDecimal a = new BigDecimal(1);
        final BigDecimal b = new BigDecimal(1);
        final BigDecimal c = new BigDecimal(1);
        final BigDecimal d = new BigDecimal(-1);

        Plain plain = new Plain(a, b, c, d);
        final BigDecimal angleActual = plainCalculator.angleToXAxis(plain);

        Assert.assertEquals(angleExpected, angleActual);
    }

    @Test
    public void angleToYAxis_shouldReturnCorrectValue_whenPlainIsFromCoefficients() {
        final BigDecimal angleExpected = new BigDecimal("0.61548");
        final BigDecimal a = new BigDecimal(1);
        final BigDecimal b = new BigDecimal(1);
        final BigDecimal c = new BigDecimal(1);
        final BigDecimal d = new BigDecimal(-1);

        Plain plain = new Plain(a, b, c, d);
        final BigDecimal angleActual = plainCalculator.angleToYAxis(plain);

        Assert.assertEquals(angleExpected, angleActual);
    }

    @Test
    public void angleToZAxis_shouldReturnCorrectValue_whenPlainIsFromCoefficients() {
        final BigDecimal angleExpected = new BigDecimal("0.61548");
        final BigDecimal a = new BigDecimal(1);
        final BigDecimal b = new BigDecimal(1);
        final BigDecimal c = new BigDecimal(1);
        final BigDecimal d = new BigDecimal(-1);

        Plain plain = new Plain(a, b, c, d);
        final BigDecimal angleActual = plainCalculator.angleToZAxis(plain);

        Assert.assertEquals(angleExpected, angleActual);
    }

    @Test
    public void doThreePointFormPlane_shouldReturnTrue() {
        final Point3d POINT_3_D_1 = new Point3d(
                new BigDecimal(1),
                new BigDecimal(3),
                new BigDecimal(5)
        );
        final Point3d POINT_3_D_2 = new Point3d(
                new BigDecimal(1),
                new BigDecimal(4),
                new BigDecimal(7)
        );
        final Point3d POINT_3_D_3 = new Point3d(
                new BigDecimal(-5),
                new BigDecimal(2),
                new BigDecimal(1)
        );
        boolean state = plainCalculator.isPlane(POINT_3_D_1, POINT_3_D_2, POINT_3_D_3);

        Assert.assertTrue(state);
    }

    @Test
    public void isPerpendicularToXAxis_shouldReturnFalse() {
        final BigDecimal a = new BigDecimal(1);
        final BigDecimal b = new BigDecimal(1);
        final BigDecimal c = new BigDecimal(1);
        final BigDecimal d = new BigDecimal(-1);

        Plain plain = new Plain(a, b, c, d);
        final boolean state = plainCalculator.isPerpendicularToXAxis(plain);

        Assert.assertFalse(state);
    }

    @Test
    public void isPerpendicularToYAxis_shouldReturnFalse() {
        final BigDecimal a = new BigDecimal(1);
        final BigDecimal b = new BigDecimal(1);
        final BigDecimal c = new BigDecimal(1);
        final BigDecimal d = new BigDecimal(-1);

        Plain plain = new Plain(a, b, c, d);
        final boolean state = plainCalculator.isPerpendicularToYAxis(plain);

        Assert.assertFalse(state);
    }

    @Test
    public void isPerpendicularToZAxis_shouldReturnFalse() {
        final BigDecimal a = new BigDecimal(1);
        final BigDecimal b = new BigDecimal(1);
        final BigDecimal c = new BigDecimal(1);
        final BigDecimal d = new BigDecimal(-1);

        Plain plain = new Plain(a, b, c, d);
        final boolean state = plainCalculator.isPerpendicularToZAxis(plain);

        Assert.assertFalse(state);
    }
}
