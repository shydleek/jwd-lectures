package org.epam.jwd.model;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.util.ApplicationConstants;
import org.epam.jwd.validation.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlainTest {
    @Test
    public void Plain_shouldCreatePlainObjectFromPoints() {
        final Point3d POINT_3_d_1 = new Point3d(1,3,5);
        final Point3d POINT_3_d_2 = new Point3d(1,4,7);
        final Point3d POINT_3_d_3 = new Point3d(-5,2,1);

        Plain plain = null;
        try {
            plain = new Plain(POINT_3_d_1, POINT_3_d_2, POINT_3_d_3);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNotNull(plain);
    }

    @Test
    public void Plain_shouldCreatePlainObjectFromCoefficients() {
        final List<Double> correctResult = Validator.validateCoefficientsFromFile(
                ApplicationConstants.PATH_TO_CORRECT_FILE_COEFFCIENT.getValue()
        );
        final double A = correctResult.get(0);
        final double B = correctResult.get(1);
        final double C = correctResult.get(2);
        final double D = correctResult.get(3);

        Plain plain = null;
        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNotNull(plain);
    }

    @Test
    public void Plain_shouldNotCreatePlainObjectFromPoints_pointsAreComplanar() {
        final Point3d POINT_3_d_1 = new Point3d(1,3,5);
        final Point3d POINT_3_d_2 = new Point3d(2,6,10);
        final Point3d POINT_3_d_3 = new Point3d(-1,-3,-5);

        Plain plain = null;
        try {
            plain = new Plain(POINT_3_d_1, POINT_3_d_2, POINT_3_d_3);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNull(plain);
    }

    @Test
    public void Plain_shouldNotCreatePlainObjectFromCoefficients_allCoefficientsAreZeros() {
        final List<Double> incorrectResult = Validator.validateCoefficientsFromFile(
                ApplicationConstants.PATH_TO_INCORRECT_FILE_COEFFCIENT.getValue()
        );
        final double A = incorrectResult.get(0);
        final double B = incorrectResult.get(1);
        final double C = incorrectResult.get(2);
        final double D = incorrectResult.get(3);

        Plain plain = null;
        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            System.out.println(e.getMessage());
        }
        Assert.assertNull(plain);
    }
}
