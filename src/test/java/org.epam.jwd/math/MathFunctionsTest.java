package org.epam.jwd.math;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.util.ApplicationConstants;
import org.epam.jwd.validation.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MathFunctionsTest {
    private static final double DELTA = 1e-6;
    private static final List<Double> correctResultCoefficients = Validator.validateCoefficientsFromFile(
            ApplicationConstants.PATH_TO_CORRECT_FILE_COEFFCIENT.getValue()
    );
    private static final double A = correctResultCoefficients.get(0);
    private static final double B = correctResultCoefficients.get(1);
    private static final double C = correctResultCoefficients.get(2);
    private static final double D = correctResultCoefficients.get(3);
//    private static final List<Double> correctResultPoints = Validator.validateCoefficientsFromFile(
//            ApplicationConstants.PATH_TO_CORRECT_FILE_COEFFCIENT.getValue()
//    );
//    private static final double A = correctResultPoints.get(0);
//    private static final double B = correctResultPoints.get(1);
//    private static final double C = correctResultPoints.get(2);
//    private static final double D = correctResultPoints.get(3);

    @Test
    public void angleToXAxis_shouldReturnDoubleValue() {
        final double angleExpected = 0.841069;
        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }

        final double angleActual = MathFunctions.angleToXAxis(plain);

        Assert.assertEquals(angleExpected, angleActual, DELTA);
    }

    @Test
    public void angleToYAxis_shouldReturnDoubleValue() {
        final double angleExpected = 0.841069;
        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }

        final double angleActual = MathFunctions.angleToYAxis(plain);

        Assert.assertEquals(angleExpected, angleActual, DELTA);
    }

    @Test
    public void angleToZAxis_shouldReturnDoubleValue() {
        final double angleExpected = 1.23096;
        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }

        final double angleActual = MathFunctions.angleToZAxis(plain);

        Assert.assertEquals(angleExpected, angleActual, DELTA);
    }

    @Test
    public void doThreePointFormPlane_shouldReturnTrue() throws PlainNotExist {
        final Point3d POINT_3_D_1 = new Point3d(1,3,5);
        final Point3d POINT_3_D_2 = new Point3d(1,4,7);
        final Point3d POINT_3_D_3 = new Point3d(-5,2,1);

        final boolean state = MathFunctions.doThreePointsFormPlane(POINT_3_D_1, POINT_3_D_2, POINT_3_D_3);

        Assert.assertTrue(state);
    }

    @Test
    public void isPerpendicularToXAxis_shouldReturnFalse() {
        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }

        final boolean state = MathFunctions.isPerpendicularToXAxis(plain);

        Assert.assertFalse(state);
    }

    @Test
    public void isPerpendicularToYAxis_shouldReturnFalse() {
        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }

        final boolean state = MathFunctions.isPerpendicularToYAxis(plain);

        Assert.assertFalse(state);
    }

    @Test
    public void isPerpendicularToZAxis_shouldReturnFalse() {
        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }

        final boolean state = MathFunctions.isPerpendicularToZAxis(plain);

        Assert.assertFalse(state);
    }
}
