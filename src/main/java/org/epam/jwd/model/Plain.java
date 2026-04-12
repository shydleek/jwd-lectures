package org.epam.jwd.model;

import org.epam.jwd.app.Main;
import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.util.ApplicationConstants;
import org.epam.jwd.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Plain {
    private final double a;
    private final double b;
    private final double c;
    private final double d;

    private static final Logger LOG = LoggerFactory.getLogger(Plain.class);

    public Plain(double a, double b, double c, double d) throws PlainNotExist {
        if (a != 0 && b != 0 && c != 0){
            if (a != 1) {
                this.a = 1;
                this.b = b / a;
                this.c = c / a;
                this.d = d / a;
            } else {
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d;
            }
        } else {
            throw new PlainNotExist(ApplicationConstants.ERR_PLAIN_COEFFS.getValue());
        }
    }

    public Plain(Point p1, Point p2, Point p3) throws PlainNotExist {
        double a = 0.0d;
        double b = 0.0d;
        double c = 0.0d;
        double d = 0.0d;

        final Vector vector1 = new Vector(p1, p2);
        final Vector vector2 = new Vector(p1, p3);
        final Vector n = Vector.crossProduct(vector1, vector2);


        if (n.getX() != 0 && n.getY() != 0 && n.getZ() != 0) {
            a = (p2.getY()-p1.getY())*(p3.getZ()-p1.getZ())-(p2.getZ()-p1.getZ())*(p3.getY()-p1.getY());
            b = (p2.getZ()-p1.getZ())*(p3.getX()-p1.getX())-(p2.getX()-p1.getX())*(p3.getZ()-p1.getZ());
            c = (p2.getX()-p1.getX())*(p3.getY()-p1.getY())-(p2.getY()-p1.getY())*(p3.getX()-p1.getX());
            d = -(a * p1.getX() + b * p1.getY() + c * p1.getZ());

            if (a != 1) {
                this.a = 1;
                this.b = b / a;
                this.c = c / a;
                this.d = d / a;
            } else {
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d;
            }
        } else {
            throw new PlainNotExist(ApplicationConstants.ERR_PLAIN_POINTS.getValue());
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
                "}, or " + a +
                "x + " + b +
                "y + " + c +
                "z + " + d +
                " = 0.";
    }
}
