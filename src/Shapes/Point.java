package Shapes;

import GameMain.Compare;

/**
 * the class represent Shapes.Point, Shapes.Point have x and y values.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * .
     * the method build new point with the given x and y values
     *
     * @param x the x of the point
     * @param y the y of the point
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * .
     * the method return the distance of this point to the other point
     *
     * @param other the method get other point
     * @return distance of this point to the other point
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * .
     * the method check if 2 point are equals
     *
     * @param other the method get other point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        return ((Compare.doubleEquals(x1, x2)) && (Compare.doubleEquals(y1, y2)));
    }

    /**
     * .
     * Return the x value of this point
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * .
     * Return the y value of this point
     *
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}