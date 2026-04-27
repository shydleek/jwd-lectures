package org.epam.jwd.model;

import org.epam.jwd.validation.PlainValidator;

import java.math.BigDecimal;
import java.util.Objects;

public class Plain {
    private final BigDecimal a;
    private final BigDecimal b;
    private final BigDecimal c;
    private final BigDecimal d;

    private static final PlainValidator PLAIN_VALIDATOR = PlainValidator.getInstance();

    public Plain(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d) {
        PLAIN_VALIDATOR.areCoefficientsValidated(a, b, c);

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Plain(Point3d a, Point3d b, Point3d c) {
        PLAIN_VALIDATOR.arePointsValidated(a, b, c);

        this.a = getPlaneCoefficientAThroughPoints(a, b, c);
        this.b = getPlaneCoefficientBThroughPoints(a, b, c);
        this.c = getPlaneCoefficientCThroughPoints(a, b, c);
        this.d = getPlaneCoefficientDThroughPoints(a, b, c);
    }

    public BigDecimal getA() {
        return a;
    }

    public BigDecimal getB() {
        return b;
    }

    public BigDecimal getC() {
        return c;
    }

    public BigDecimal getD() {
        return d;
    }
    // TODO: refactor these 4 methods
    public BigDecimal getPlaneCoefficientAThroughPoints(Point3d a, Point3d b, Point3d c) {
        BigDecimal first = b.getY().subtract(a.getY());
        BigDecimal second = c.getZ().subtract(a.getZ());
        BigDecimal fs = first.multiply(second);
        BigDecimal third = b.getZ().subtract(a.getZ());
        BigDecimal fourth = c.getY().subtract(a.getY());
        BigDecimal tf = third.multiply(fourth);
        return fs.subtract(tf);
//        return b.getY().subtract(a.getY()).multiply(c.getZ().subtract(a.getZ()))
//                .subtract(b.getZ().subtract(a.getZ())).multiply(c.getY().subtract(a.getY()));
    }

    public BigDecimal getPlaneCoefficientBThroughPoints(Point3d a, Point3d b, Point3d c) {
        BigDecimal first = b.getZ().subtract(a.getZ());
        BigDecimal second = c.getX().subtract(a.getX());
        BigDecimal fs = first.multiply(second);
        BigDecimal third = b.getX().subtract(a.getX());
        BigDecimal fourth = c.getZ().subtract(a.getZ());
        BigDecimal tf = third.multiply(fourth);
        return fs.subtract(tf);
        //        return b.getZ().subtract(a.getZ()).multiply(c.getX().subtract(a.getX()))
//                .subtract(b.getX().subtract(a.getX())).multiply(c.getZ().subtract(a.getZ()));
    }

    public BigDecimal getPlaneCoefficientCThroughPoints(Point3d a, Point3d b, Point3d c) {
        BigDecimal first = b.getX().subtract(a.getX());
        BigDecimal second = c.getY().subtract(a.getY());
        BigDecimal fs = first.multiply(second);
        BigDecimal third = b.getY().subtract(a.getY());
        BigDecimal fourth = c.getX().subtract(a.getX());
        BigDecimal tf = third.multiply(fourth);
        return fs.subtract(tf);
//        return b.getX().subtract(a.getX()).multiply(c.getY().subtract(a.getY()))
//                .subtract(b.getY().subtract(a.getY())).multiply(c.getX().subtract(a.getX()));
    }

    public BigDecimal getPlaneCoefficientDThroughPoints(Point3d a, Point3d b, Point3d c) {
        return getPlaneCoefficientAThroughPoints(a, b, c).multiply(a.getX())
                .add(getPlaneCoefficientBThroughPoints(a, b, c).multiply(a.getY()))
                .add(getPlaneCoefficientCThroughPoints(a, b, c).multiply(a.getZ())).negate();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plain plain = (Plain) o;
        return Objects.equals(a, plain.a) && Objects.equals(b, plain.b) && Objects.equals(c, plain.c) && Objects.equals(d, plain.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }

    @Override
    public String toString() {
        return "Plain{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                "}, or " + a +
                "x + " + b +
                "y + " + c +
                "z + " + d +
                " = 0.";
    }
}