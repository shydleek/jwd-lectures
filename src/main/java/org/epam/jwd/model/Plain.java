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

    public BigDecimal getPlaneCoefficientAThroughPoints(Point3d a, Point3d b, Point3d c) {
        BigDecimal deltaYab = b.getY().subtract(a.getY());
        BigDecimal deltaZac = c.getZ().subtract(a.getZ());
        BigDecimal firstProduct = deltaYab.multiply(deltaZac);
        BigDecimal deltaZab = b.getZ().subtract(a.getZ());
        BigDecimal deltaYac = c.getY().subtract(a.getY());
        BigDecimal secondProduct = deltaZab.multiply(deltaYac);
        return firstProduct.subtract(secondProduct);
    }

    public BigDecimal getPlaneCoefficientBThroughPoints(Point3d a, Point3d b, Point3d c) {
        BigDecimal deltaZab = b.getZ().subtract(a.getZ());
        BigDecimal deltaXac = c.getX().subtract(a.getX());
        BigDecimal firstProduct = deltaZab.multiply(deltaXac);
        BigDecimal deltaXab = b.getX().subtract(a.getX());
        BigDecimal deltaZac = c.getZ().subtract(a.getZ());
        BigDecimal secondProduct = deltaXab.multiply(deltaZac);
        return firstProduct.subtract(secondProduct);
    }

    public BigDecimal getPlaneCoefficientCThroughPoints(Point3d a, Point3d b, Point3d c) {
        BigDecimal deltaZab = b.getX().subtract(a.getX());
        BigDecimal deltaYac = c.getY().subtract(a.getY());
        BigDecimal firstProduct = deltaZab.multiply(deltaYac);
        BigDecimal deltaYab = b.getY().subtract(a.getY());
        BigDecimal deltaXac = c.getX().subtract(a.getX());
        BigDecimal secondProduct = deltaYab.multiply(deltaXac);
        return firstProduct.subtract(secondProduct);
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