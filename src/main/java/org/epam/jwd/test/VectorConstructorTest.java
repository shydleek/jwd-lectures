package org.epam.jwd.test;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Point;
import org.epam.jwd.model.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VectorConstructorTest {
    private static final Logger LOG = LoggerFactory.getLogger(VectorConstructorTest.class);
    private static final Point A = new Point(0.0, 0.0, 0.0);
    private static final Point B = new Point(0.0, 0.0, 0.0);


    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
        LOG.info("Testing... in test()");
        new Vector(A, B);
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
