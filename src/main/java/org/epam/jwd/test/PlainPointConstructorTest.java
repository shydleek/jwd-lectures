package org.epam.jwd.test;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point;
import org.epam.jwd.util.ApplicationConstants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlainPointConstructorTest {

    private static final Logger LOG = LoggerFactory.getLogger(PlainPointConstructorTest.class);
    private static final Point POINT_1 = new Point(1,3,5);
    private static final Point POINT_2 = new Point(1,4,7);
    private static final Point POINT_3 = new Point(-5,2,1);

    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
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
