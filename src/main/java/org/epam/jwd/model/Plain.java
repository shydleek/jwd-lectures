package org.epam.jwd.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Plain extends Point {
    private double a;
    private double b;
    private double c;

    private static final Logger LOG = LoggerFactory.getLogger(Plain.class);
    private static final String ERR_PLAIN = "Plain does not exists (a^2 + b^2 + c^2 >0)";

    public Plain(double a, double b, double c){
        if (a != 0 && b != 0 && c != 0){
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            LOG.error(ERR_PLAIN);
        }
    }

    public Plain(Point p1, Point p2, Point p3){
        final double v_1_x = p2.getX() - p1.getX();
        final double v_1_y = p2.getY() - p1.getY();
        final double v_2_x = p3.getX() - p1.getX();
        final double v_2_y = p3.getY() - p1.getY();
        final double plain = v_1_x*v_2_y-v_1_y*v_2_x;
        if (plain != 0) {
            //this.a =
            //this.b =
            //this.c =
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Plain plain = (Plain) o;
        return Double.compare(a, plain.a) == 0 && Double.compare(b, plain.b) == 0 && Double.compare(c, plain.c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), a, b, c);
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
