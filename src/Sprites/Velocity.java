package Sprites;

import Shapes.Point;

/**
 * the class represents velocity, Sprite.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private static final int RIGHT_ANGLE = 90;
    private final double dx;
    private final double dy;

    /**
     * .
     * constructor
     *
     * @param dx dx of the velocity
     * @param dy dy of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * .
     * constructor
     */
    public Velocity() {
        this.dx = 0;
        this.dy = 0;
    }

    /**
     * .
     * the method converts angle and speed to dx and dy
     *
     * @param angle angle of the vector
     * @param speed speed of the vector
     * @return velocity v
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - RIGHT_ANGLE);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * .
     * the method return dx
     *
     * @return dx
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * .
     * the method return dy
     *
     * @return dy
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * .
     * the method change the position of the point in accordance of the velocity
     *
     * @param p point with position (x,y)
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDX(), p.getY() + this.getDY());
    }
}
