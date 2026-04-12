package org.epam.jwd.test;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlainErrorPointTest {
    private static final Logger LOG = LoggerFactory.getLogger(PlainErrorPointTest.class);
    private static final Point POINT_1 = new Point(1,3,5);
    private static final Point POINT_2 = new Point(2,6,10);
    private static final Point POINT_3 = new Point(-1,-3,-5);

    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() {
        LOG.info("Testing... in test()");
        try {
            new Plain(POINT_1, POINT_2, POINT_3);
        } catch (PlainNotExist e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
