package org.epam.jwd.validation;

import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;

import java.math.BigDecimal;

public class PlainValidator {
    private static PlainValidator instance;
    private final PlainCalculator plainCalculator = PlainCalculator.getInstance();

    private PlainValidator() {
    }

    public static PlainValidator getInstance() {
        if (instance == null) {
            instance = new PlainValidator();
        }
        return instance;
    }

    public boolean isPlaneValidated(Plain plain) {
        Point3d n = crossProduct(
                getVectorFromPoints(plain.getA(), plain.getB()),
                getVectorFromPoints(plain.getA(), plain.getC())
        );

        return n.getX().compareTo(BigDecimal.ZERO) != 0
                || n.getY().compareTo(BigDecimal.ZERO) != 0
                || n.getZ().compareTo(BigDecimal.ZERO) != 0;
    }

    private Point3d getVectorFromPoints(Point3d a, Point3d b) {
        return new Point3d(
                b.getX().subtract(a.getX()),
                b.getY().subtract(a.getY()),
                b.getZ().subtract(a.getZ())
        );
    }

    private Point3d crossProduct(Point3d firstVector, Point3d secondVector) {
        return new Point3d(
                firstVector.getY().multiply(secondVector.getZ())
                        .subtract(firstVector.getZ().multiply(secondVector.getY())),
                firstVector.getZ().multiply(secondVector.getX())
                        .subtract(firstVector.getX().multiply(secondVector.getZ())),
                firstVector.getX().multiply(secondVector.getY())
                        .subtract(firstVector.getY().multiply(secondVector.getX()))
        );
    }
}
