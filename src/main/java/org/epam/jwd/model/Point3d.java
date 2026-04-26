package org.epam.jwd.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Point3d {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    public Point3d(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point3d point3d = (Point3d) o;
        return Objects.equals(x, point3d.x) && Objects.equals(y, point3d.y) && Objects.equals(z, point3d.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}