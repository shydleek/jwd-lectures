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

    public static class NonParameterizedTests {
        @Test
        public void angleToXAxis_shouldReturnCorrectValue_whenPlainIsValidated() {
            final BigDecimal angleExpected = new BigDecimal("0.61548");

            final BigDecimal angleActual = PLAIN_CALCULATOR.angleToXAxis(initPlain());

            Assert.assertEquals(angleExpected, angleActual);
        }

        @Test
        public void angleToYAxis_shouldReturnCorrectValue_whenPlainIsValidated() {
            final BigDecimal angleExpected = new BigDecimal("0.61548");

            final BigDecimal angleActual = PLAIN_CALCULATOR.angleToYAxis(initPlain());

            Assert.assertEquals(angleExpected, angleActual);
        }

        @Test
        public void angleToZAxis_shouldReturnCorrectValue_whenPlainIsValidated() {
            final BigDecimal angleExpected = new BigDecimal("0.61548");

            final BigDecimal angleActual = PLAIN_CALCULATOR.angleToZAxis(initPlain());

            Assert.assertEquals(angleExpected, angleActual);
        }

        @Test
        public void isPlane_shouldReturnTrue() {
            Assert.assertTrue(PLAIN_CALCULATOR.isPlane(initRandomPlain()));
        }

        private Plain initPlain() {
            final Point3d a = new Point3d(new BigDecimal(0), new BigDecimal(0), new BigDecimal(1));
            final Point3d b = new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0));
            final Point3d c = new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0));

            return new Plain(a, b, c);
        }

        private Plain initRandomPlain() {
            final Point3d a = new Point3d(
                    new BigDecimal(1),
                    new BigDecimal(3),
                    new BigDecimal(5)
            );
            final Point3d b = new Point3d(
                    new BigDecimal(1),
                    new BigDecimal(4),
                    new BigDecimal(7)
            );
            final Point3d c = new Point3d(
                    new BigDecimal(-5),
                    new BigDecimal(2),
                    new BigDecimal(1)
            );

            return new Plain(a, b, c);
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
        public void isPerpendicularToXAxis_shouldReturnFalse() {
            Assert.assertFalse(PLAIN_CALCULATOR.isPerpendicularToXAxis(plain));
        }

        @Test
        public void isPerpendicularToYAxis_shouldReturnFalse() {
            Assert.assertFalse(PLAIN_CALCULATOR.isPerpendicularToYAxis(plain));
        }

        @Test
        public void isPerpendicularToZAxis_shouldReturnFalse() {
            Assert.assertFalse(PLAIN_CALCULATOR.isPerpendicularToZAxis(plain));
        }
    }
}