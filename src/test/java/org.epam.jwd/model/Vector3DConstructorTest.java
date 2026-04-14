package org.epam.jwd.model;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Point3D;
import org.epam.jwd.model.Vector3D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vector3DConstructorTest {
    private static final Logger LOG = LoggerFactory.getLogger(Vector3DConstructorTest.class);
    private static final Point3D A = new Point3D(0.0, 0.0, 0.0);
    private static final Point3D B = new Point3D(0.0, 0.0, 0.0);


    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
        LOG.info("Testing... in test()");
        new Vector3D(A, B);
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
