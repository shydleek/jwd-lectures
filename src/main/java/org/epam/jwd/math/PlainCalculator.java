package org.epam.jwd.math;

import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.holder.CalculationsHolder;
import org.epam.jwd.model.Plain;
import org.epam.jwd.validation.PlainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class PlainCalculator {

    private static PlainCalculator instance;

    private static final int EXPONENT = 2;
    private static final int PRECISION = 10;
    private static final int PRECISION_FOR_SCALE = 4;
    private static final MathContext MATH_CONTEXT = new MathContext(PRECISION, RoundingMode.HALF_UP);
    private static final BigDecimal RIGHT_ANGLE = BigDecimal.valueOf(Math.PI / 2).setScale(PRECISION_FOR_SCALE, RoundingMode.HALF_UP);

    private final PlainValidator plainValidator;

    private PlainCalculator(PlainValidator plainValidator) {
        this.plainValidator = plainValidator;
    }

    public static PlainCalculator getInstance() {
        if (instance == null) {
            instance = new PlainCalculator(PlainValidator.getInstance());
        }
        return instance;
    }

    public BigDecimal angleToXAxis(Plain plain) {
        if(isPlane(plain)) {
            return calculateAsin(
                    getCoefficientA(plain).abs()
                            .divide(
                                    getCoefficientA(plain).pow(EXPONENT)
                                            .add(getCoefficientB(plain).pow(EXPONENT))
                                            .add(getCoefficientC(plain).pow(EXPONENT))
                                            .sqrt(MATH_CONTEXT),
                                    MATH_CONTEXT
                            )
            ).setScale(PRECISION_FOR_SCALE, RoundingMode.HALF_UP);
        } else {
            throw new ValidationException();
        }
    }

    public BigDecimal angleToYAxis(Plain plain) {
        if(isPlane(plain)) {
            return calculateAsin(
                    getCoefficientB(plain).abs()
                            .divide(
                                    getCoefficientA(plain).pow(EXPONENT)
                                            .add(getCoefficientB(plain).pow(EXPONENT))
                                            .add(getCoefficientC(plain).pow(EXPONENT))
                                            .sqrt(MATH_CONTEXT),
                                    MATH_CONTEXT
                            )
            ).setScale(PRECISION_FOR_SCALE, RoundingMode.HALF_UP);
        } else {
            throw new ValidationException();
        }
    }

    public BigDecimal angleToZAxis(Plain plain) {
        if(isPlane(plain)) {
            return calculateAsin(
                    getCoefficientC(plain).abs()
                            .divide(
                                    getCoefficientA(plain).pow(EXPONENT)
                                            .add(getCoefficientB(plain).pow(EXPONENT))
                                            .add(getCoefficientC(plain).pow(EXPONENT))
                                            .sqrt(MATH_CONTEXT),
                                    MATH_CONTEXT
                            )
            ).setScale(PRECISION_FOR_SCALE, RoundingMode.HALF_UP);
        } else {
            throw new ValidationException();
        }
    }

    private boolean isPlane(Plain plain) {
        return plainValidator.isPlaneValid(plain);
    }

    public boolean isPerpendicularToXAxis(Plain plain) {
        return RIGHT_ANGLE.compareTo(angleToXAxis(plain)) == 0.0;
    }

    public boolean isPerpendicularToYAxis(Plain plain) {
        return RIGHT_ANGLE.compareTo(angleToYAxis(plain)) == 0.0;
    }

    public boolean isPerpendicularToZAxis(Plain plain) {
        return RIGHT_ANGLE.compareTo(angleToZAxis(plain)) == 0.0;
    }

    private BigDecimal calculateAsin(BigDecimal value) {
        double doubleValue = value.doubleValue();
        double asinResult = Math.asin(doubleValue);
        return new BigDecimal(String.valueOf(asinResult));
    }

    private BigDecimal getCoefficientA(Plain plain) {
        BigDecimal deltaYab = plain.getB().getY().subtract(plain.getA().getY());
        BigDecimal deltaZac = plain.getC().getZ().subtract(plain.getA().getZ());
        BigDecimal firstProduct = deltaYab.multiply(deltaZac);
        BigDecimal deltaZab = plain.getB().getZ().subtract(plain.getA().getZ());
        BigDecimal deltaYac = plain.getC().getY().subtract(plain.getA().getY());
        BigDecimal secondProduct = deltaZab.multiply(deltaYac);
        return firstProduct.subtract(secondProduct);
    }

    private BigDecimal getCoefficientB(Plain plain) {
        BigDecimal deltaZab = plain.getB().getZ().subtract(plain.getA().getZ());
        BigDecimal deltaXac = plain.getC().getX().subtract(plain.getA().getX());
        BigDecimal firstProduct = deltaZab.multiply(deltaXac);
        BigDecimal deltaXab = plain.getB().getX().subtract(plain.getA().getX());
        BigDecimal deltaZac = plain.getC().getZ().subtract(plain.getA().getZ());
        BigDecimal secondProduct = deltaXab.multiply(deltaZac);
        return firstProduct.subtract(secondProduct);
    }

    private BigDecimal getCoefficientC(Plain plain) {
        BigDecimal deltaZab = plain.getB().getX().subtract(plain.getA().getX());
        BigDecimal deltaYac = plain.getC().getY().subtract(plain.getA().getY());
        BigDecimal firstProduct = deltaZab.multiply(deltaYac);
        BigDecimal deltaYab = plain.getB().getY().subtract(plain.getA().getY());
        BigDecimal deltaXac = plain.getC().getX().subtract(plain.getA().getX());
        BigDecimal secondProduct = deltaYab.multiply(deltaXac);
        return firstProduct.subtract(secondProduct);
    }

    private BigDecimal getCoefficientD(Plain plain) {
        return getCoefficientA(plain).multiply(plain.getA().getX())
                .add(getCoefficientB(plain).multiply(plain.getA().getY()))
                .add(getCoefficientC(plain).multiply(plain.getA().getZ())).negate();
    }

    public CalculationsHolder calculate(Plain plain) {
        return new CalculationsHolder(
                angleToXAxis(plain),
                angleToYAxis(plain),
                angleToZAxis(plain),
                isPlane(plain),
                isPerpendicularToXAxis(plain),
                isPerpendicularToYAxis(plain),
                isPerpendicularToZAxis(plain)
        );
    }
}