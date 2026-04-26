package org.epam.jwd.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Vector3d {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal z;

    public Vector3d(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d crossProduct(Vector3d secondVector){
        return new Vector3d(this.getY().multiply(secondVector.getZ()).subtract(this.getZ().multiply(secondVector.getY())),
                this.getZ().multiply(secondVector.getX()).subtract(this.getX().multiply(secondVector.getZ())),
                this.getX().multiply(secondVector.getY()).subtract(this.getY().multiply(secondVector.getX()))
        );
    }

    public Vector3d(Point3d p1, Point3d p2) {
        this.x = p2.getX().subtract(p1.getX());
        this.y = p2.getY().subtract(p1.getY());
        this.z = p2.getZ().subtract(p1.getZ());
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
        Vector3d vector3d = (Vector3d) o;
        return Objects.equals(x, vector3d.x) && Objects.equals(y, vector3d.y) && Objects.equals(z, vector3d.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
