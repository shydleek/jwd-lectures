package org.epam.jwd.model;

import org.junit.Assert;
import org.junit.Test;

public class Point3dTest {
    private static final double A = 0.0;
    private static final double B = 0.0;
    private static final double C = 0.0;

    @Test
    public void Point3d_shouldCreatePoint3dObject() {
        Point3d point = new Point3d(A, B, C);
        Assert.assertNotNull(point);
    }
}
