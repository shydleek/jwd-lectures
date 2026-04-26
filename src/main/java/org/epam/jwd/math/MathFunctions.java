package org.epam.jwd.math;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.validation.PlainValidator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class MathFunctions {

    private static final int EXPONENT = 2;
    private static final int PRECISION = 5;
    private static final BigDecimal RIGHT_ANGLE = BigDecimal.valueOf(Math.PI / 2);
    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);
    private static final PlainValidator PLAIN_VALIDATOR = new PlainValidator();

    public MathFunctions() {}

    public BigDecimal angleToXAxis(Plain plain) {
        return calculateAsin(
                plain.getA().abs()
                        .divide(
                                plain.getA().pow(EXPONENT)
                                        .add(plain.getB().pow(EXPONENT))
                                        .add(plain.getC().pow(EXPONENT))
                                        .sqrt(MATH_CONTEXT),
                                MATH_CONTEXT
                        )
        ).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    public BigDecimal angleToYAxis(Plain plain) {
        return calculateAsin(
                plain.getB().abs()
                        .divide(
                                plain.getA().pow(EXPONENT)
                                        .add(plain.getB().pow(EXPONENT))
                                        .add(plain.getC().pow(EXPONENT))
                                        .sqrt(MATH_CONTEXT),
                                MATH_CONTEXT
                        )
        ).setScale(PRECISION, RoundingMode.HALF_UP);
    }
    public BigDecimal angleToZAxis(Plain plain) {
        return calculateAsin(
                plain.getC().abs()
                        .divide(
                                plain.getA().pow(EXPONENT)
                                        .add(plain.getB().pow(EXPONENT))
                                        .add(plain.getC().pow(EXPONENT))
                                        .sqrt(MATH_CONTEXT),
                                MATH_CONTEXT
                        )
        ).setScale(PRECISION, RoundingMode.HALF_UP);
    }

    public boolean doThreePointsFormPlane(Point3d a, Point3d b, Point3d c) {
        return PLAIN_VALIDATOR.arePointsValidated(a, b, c);
    }

    public boolean isPerpendicularToXAxis(Plain plain) {
        return RIGHT_ANGLE.compareTo(angleToXAxis(plain)) == 0;
    }

    public boolean isPerpendicularToYAxis(Plain plain) {
        return RIGHT_ANGLE.compareTo(angleToYAxis(plain)) == 0;
    }

    public boolean isPerpendicularToZAxis(Plain plain) {
        return RIGHT_ANGLE.compareTo(angleToZAxis(plain)) == 0;
    }
    // TODO: протестировать
    public BigDecimal calculateAsin(BigDecimal value) {
        double doubleValue = value.doubleValue();
        double asinResult = Math.asin(doubleValue);
        return new BigDecimal(String.valueOf(asinResult));
    }
}
