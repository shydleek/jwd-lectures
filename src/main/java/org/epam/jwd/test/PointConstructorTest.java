package org.epam.jwd.test;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointConstructorTest {
    private static final Logger LOG = LoggerFactory.getLogger(PointConstructorTest.class);
    private static final double A = 0.0;
    private static final double B = 0.0;
    private static final double C = 0.0;


    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
        LOG.info("Testing... in test()");
        new Point(A, B, C);
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
