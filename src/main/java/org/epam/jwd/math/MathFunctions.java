package org.epam.jwd.math;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MathFunctions {
    private static final Logger LOG = LoggerFactory.getLogger(MathFunctions.class);
    private static final int exponent = 2;
    private static final double rightAngle = Math.PI / 2;

    public static double angleToXAxis(Plain plain) {
        return Math.acos(
                Math.abs(plain.getA()) /
                Math.sqrt(
                        Math.pow(plain.getA(),exponent) +
                        Math.pow(plain.getB(),exponent) +
                        Math.pow(plain.getC(),exponent)
                )
        );
    }
    public static double angleToYAxis(Plain plain) {
        return Math.acos(
                Math.abs(plain.getB()) /
                Math.sqrt(
                        Math.pow(plain.getA(),exponent) +
                        Math.pow(plain.getB(),exponent) +
                        Math.pow(plain.getC(),exponent)
                )
        );
    }
    public static double angleToZAxis(Plain plain) {
        return Math.acos(
                Math.abs(plain.getC()) /
                Math.sqrt(
                        Math.pow(plain.getA(),exponent) +
                        Math.pow(plain.getB(),exponent) +
                        Math.pow(plain.getC(),exponent)
                )
        );
    }

    public static boolean doThreePointsFormPlane(Point3d p1, Point3d p2, Point3d p3) throws PlainNotExist {
        Plain plain = null;
        try {
            plain = new Plain(p1, p2, p3);
        } catch (PlainNotExist e) {
            LOG.error(e.getMessage(), e);
        }
        return plain != null;
    }

    public static boolean isPerpendicularToXAxis(Plain plain) {
        return angleToXAxis(plain) == rightAngle;
    }
    public static boolean isPerpendicularToYAxis(Plain plain) {
        return angleToYAxis(plain) == rightAngle;
    }
    public static boolean isPerpendicularToZAxis(Plain plain) {
        return angleToZAxis(plain) == rightAngle;
    }
}
