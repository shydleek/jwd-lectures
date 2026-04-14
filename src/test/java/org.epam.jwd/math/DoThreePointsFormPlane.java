package org.epam.jwd.math;

import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.math.MathFunctions;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3D;
import org.epam.jwd.util.ApplicationConstants;
import org.epam.jwd.validation.Validator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DoThreePointsFormPlane {
    private static final Logger LOG = LoggerFactory.getLogger(DoThreePointsFormPlane.class);
    private static final List<Double> result = Validator.validateCoeffientsFromFile(
            ApplicationConstants.PATH_TO_CORRECT_FILE.getValue()
    );
    private static final Point3D POINT_3_D_1 = new Point3D(1,3,5);
    private static final Point3D POINT_3_D_2 = new Point3D(1,4,7);
    private static final Point3D POINT_3_D_3 = new Point3D(-5,2,1);


    @Before
    public void before(){
        LOG.trace("{} started!", getClass().getName());
    }

    @Test
    public void test() throws PlainNotExist {
        LOG.info("Reading from .txt");

        LOG.info("{}", result.toString());

        LOG.info("Testing... in test()");

        final boolean stateActual = MathFunctions.doThreePointsFormPlane(POINT_3_D_1, POINT_3_D_2, POINT_3_D_3);

        LOG.info("{}", stateActual);

        final boolean stateExpected = true;

        LOG.info("{}", stateExpected);

        Assert.assertEquals(stateExpected, stateActual);
    }

    @After
    public void after(){
        LOG.trace("{} ended!", getClass().getName());
    }
}
