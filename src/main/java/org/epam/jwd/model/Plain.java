package org.epam.jwd.model;

import org.epam.jwd.model.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import org.epam.jwd.model.Vector;

public class Plain {
    private final double a; //BigDecimal
    private final double b;
    private final double c;
    private final double d;

    private static final Logger LOG = LoggerFactory.getLogger(Plain.class);
    private static final String ERR_PLAIN = "Plain does not exists (a^2 + b^2 + c^2 >0)";

    public Plain(double a, double b, double c, double d){
        if (a != 0 && b != 0 && c != 0){
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        } else {
            LOG.error(ERR_PLAIN);
        }
    }

    public Plain(Point p1, Point p2, Point p3){
        final Vector vector1 = new Vector(p1, p2);
        final Vector vector2 = new Vector(p1, p3);
        final Vector n = Vector.crossProduct(vector1, vector2);

        if (n.getX() != 0 && n.getY() != 0 && n.getZ() != 0) {
            this.a = (p2.getY()-p1.getY())*(p3.getZ()-p1.getZ())-(p2.getZ()-p1.getZ())*(p3.getY()-p1.getY());
            this.b = (p2.getZ()-p1.getZ())*(p3.getX()-p1.getX())-(p2.getX()-p1.getX())*(p3.getZ()-p1.getZ());
            this.c = (p2.getX()-p1.getX())*(p3.getY()-p1.getY())-(p2.getY()-p1.getY())*(p3.getX()-p1.getX());
            this.d = -(a * p1.getX() + b * p1.getY() + c * p1.getZ());
        } else {
            LOG.error(ERR_PLAIN);
        }
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plain plain = (Plain) o;
        return Double.compare(a, plain.a) == 0 && Double.compare(b, plain.b) == 0 && Double.compare(c, plain.c) == 0 && Double.compare(d, plain.d) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }

    @Override
    public String toString() {
        return "Plain{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
