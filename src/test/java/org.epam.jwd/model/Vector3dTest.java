package org.epam.jwd.model;

import org.junit.Assert;
import org.junit.Test;

public class Vector3dTest {
    private static final Point3d A = new Point3d(0.0, 0.0, 0.0);
    private static final Point3d B = new Point3d(0.0, 0.0, 0.0);

    @Test
    public void Vector3d_shouldCreateVector3dObject() {
        Vector3d vector = new Vector3d(A, B);
        Assert.assertNotNull(vector);
    }
}
