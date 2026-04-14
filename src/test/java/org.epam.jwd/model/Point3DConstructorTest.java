package org.epam.jwd.model;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Point3D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Point3DConstructorTest {
    private static final Logger LOG = LoggerFactory.getLogger(Point3DConstructorTest.class);
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
        new Point3D(A, B, C);
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
