package org.epam.jwd.model;

import java.util.Objects;

public class Plain {

    private final Integer id;
    private Point3d a;
    private Point3d b;
    private Point3d c;

    public Plain(Integer id, Point3d a, Point3d b, Point3d c) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Plain withId(Integer id) {
        return new Plain(id, this.a, this.b, this.c);
    }

    public static Plain createPlain(Point3d a, Point3d b, Point3d c) {
        return new Plain(null, a, b, c);
    }

    public Integer getId() {
        return id;
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
        return Objects.equals(id, plain.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Plain{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}