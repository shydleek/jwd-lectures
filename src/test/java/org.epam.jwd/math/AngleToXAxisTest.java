package org.epam.jwd.math;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.math.MathFunctions;
import org.epam.jwd.model.Plain;
import org.epam.jwd.util.ApplicationConstants;
import org.epam.jwd.validation.Validator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AngleToXAxisTest {
    private static final double DELTA = 1e-6;
    private static final Logger LOG = LoggerFactory.getLogger(AngleToXAxisTest.class);
    private static final List<Double> result = Validator.validateCoeffientsFromFile(
            ApplicationConstants.PATH_TO_CORRECT_FILE.getValue()
    );
    private static final double A = result.get(0);
    private static final double B = result.get(1);
    private static final double C = result.get(2);
    private static final double D = result.get(3);


    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
        LOG.info("Reading from .txt");

        LOG.info("{}", result.toString());

        LOG.info("Testing... in test()");

        Plain plain = null;

        try {
            plain = new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            LOG.error(e.getMessage(), e);
        }

        final double angleActual = MathFunctions.angleToXAxis(plain);

        LOG.info("{}", angleActual);

        final double angleExpected = 0.841069;

        LOG.info("{}", angleExpected);

        Assert.assertEquals(angleExpected, angleActual, DELTA);
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
