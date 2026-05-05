package org.epam.jwd.math;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PlainCalculatorTest.NonParameterizedTests.class,
        PlainCalculatorTest.ParameterizedTests.class
})
public class PlainCalculatorTest {

    private static final PlainCalculator PLAIN_CALCULATOR = PlainCalculator.getInstance();

    private static final String angle = "0.6155";

    public static class NonParameterizedTests {
        @Test
        public void angleToXAxis_shouldReturnCorrectValue_whenPlainIsValidated() {
            final BigDecimal angleExpected = new BigDecimal(angle);

            final BigDecimal angleActual = PLAIN_CALCULATOR.angleToXAxis(initPlain());

            Assert.assertEquals(angleExpected, angleActual);
        }

        @Test
        public void angleToYAxis_shouldReturnCorrectValue_whenPlainIsValidated() {
            final BigDecimal angleExpected = new BigDecimal(angle);

            final BigDecimal angleActual = PLAIN_CALCULATOR.angleToYAxis(initPlain());

            Assert.assertEquals(angleExpected, angleActual);
        }

        @Test
        public void angleToZAxis_shouldReturnCorrectValue_whenPlainIsValidated() {
            final BigDecimal angleExpected = new BigDecimal(angle);

            final BigDecimal angleActual = PLAIN_CALCULATOR.angleToZAxis(initPlain());

            Assert.assertEquals(angleExpected, angleActual);
        }

        @Test
        public void isPerpendicularToXAxis_shouldReturnTrue_whenPlainIsPerpendicular() {
            Assert.assertTrue(PLAIN_CALCULATOR.isPerpendicularToXAxis(initPerpendicularToXAxisPlain()));
        }

        @Test
        public void isPerpendicularToYAxis_shouldReturnTrue_whenPlainIsPerpendicular() {
            Assert.assertTrue(PLAIN_CALCULATOR.isPerpendicularToYAxis(initPerpendicularToYAxisPlain()));
        }

        @Test
        public void isPerpendicularToZAxis_shouldReturnTrue_whenPlainIsPerpendicular() {
            Assert.assertTrue(PLAIN_CALCULATOR.isPerpendicularToZAxis(initPerpendicularToZAxisPlain()));
        }

        private Plain initPlain() {
            return new Plain(
                    new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0)),
                    new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0)),
                    new Point3d(new BigDecimal(0), new BigDecimal(0), new BigDecimal(1))
            );
        }

        private Plain initPerpendicularToXAxisPlain() {
            return new Plain(
                    new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                    new Point3d(BigDecimal.ZERO, new BigDecimal("5"), BigDecimal.ZERO),
                    new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("5"))
            );
        }

        private Plain initPerpendicularToYAxisPlain() {
            return new Plain(
                    new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                    new Point3d(new BigDecimal("5"), BigDecimal.ZERO, BigDecimal.ZERO),
                    new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("5"))
            );
        }

        private Plain initPerpendicularToZAxisPlain() {
            return new Plain(
                    new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                    new Point3d(new BigDecimal("5"), BigDecimal.ZERO, BigDecimal.ZERO),
                    new Point3d(BigDecimal.ZERO, new BigDecimal("5"), BigDecimal.ZERO)
            );
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedTests {

        private final Plain plain;

        public ParameterizedTests(Plain plain) {
            this.plain = plain;
        }

        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {
                            new Plain(
                                    new Point3d(new BigDecimal(0), new BigDecimal(0), new BigDecimal(1)),
                                    new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0)),
                                    new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0))
                            )
                    },
                    {
                            new Plain(
                                    new Point3d(new BigDecimal(1), new BigDecimal(3), new BigDecimal(5)),
                                    new Point3d(new BigDecimal(1), new BigDecimal(4), new BigDecimal(7)),
                                    new Point3d(new BigDecimal(-5), new BigDecimal(2), new BigDecimal(1))
                            )
                    }
            });
        }

        @Test
        public void isPerpendicularToXAxis_shouldReturnFalse_whenPlainIsNotPerpendicular() {
            Assert.assertFalse(PLAIN_CALCULATOR.isPerpendicularToXAxis(plain));
        }

        @Test
        public void isPerpendicularToYAxis_shouldReturnFalse_whenPlainIsNotPerpendicular() {
            Assert.assertFalse(PLAIN_CALCULATOR.isPerpendicularToYAxis(plain));
        }

        @Test
        public void isPerpendicularToZAxis_shouldReturnFalse_whenPlainIsNotPerpendicular() {
            Assert.assertFalse(PLAIN_CALCULATOR.isPerpendicularToZAxis(plain));
        }
    }
}