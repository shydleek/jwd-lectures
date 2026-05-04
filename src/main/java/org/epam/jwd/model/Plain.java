package org.epam.jwd.model;

import java.util.Objects;

public class Plain {
    private Point3d a;
    private Point3d b;
    private Point3d c;

    public Plain(Point3d a, Point3d b, Point3d c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point3d getA() {
        return a;
    }

    public Point3d getB() {
        return b;
    }

    public Point3d getC() {
        return c;
    }

    public void setA(Point3d a) {
        this.a = a;
    }

    public void setB(Point3d b) {
        this.b = b;
    }

    public void setC(Point3d c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plain plain = (Plain) o;
        return Objects.equals(a, plain.a) && Objects.equals(b, plain.b) && Objects.equals(c, plain.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "Plain{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}