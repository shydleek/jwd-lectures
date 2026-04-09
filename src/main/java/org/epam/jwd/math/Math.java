package org.epam.jwd.math;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point;

public class Math {
    public abstract double angleToHorizontalPlane(double a, double b, double c);
    public abstract double angleToVerticalPlane(double a, double b, double c);

    public abstract boolean doThreePointFormPlane(Point p1, Point p2, Point p3);

    public abstract boolean isPerpendicularToXAxis(Plain plain);
    public abstract boolean isPerpendicularToYAxis(Plain plain);
}
