package org.epam.jwd.test;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point;
import org.epam.jwd.util.ApplicationConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlainCoefficientConstructorTest {
    private static final Logger LOG = LoggerFactory.getLogger(PlainCoefficientConstructorTest.class);
    private static final int A = 1;
    private static final int B = 1;
    private static final int C = 1;
    private static final int D = 1;

    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
        LOG.info("Testing... in test()");
        try {
            new Plain(A, B, C, D);
        } catch (PlainNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
