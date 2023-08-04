package Shapes;

import GameMain.Compare;

import java.util.List;

/**
 * the class represent Shapes.Line, Shapes.Line have start point and end point.
 */
public class Line {
    private final Point start;
    private final Point end;

    // constructors

    /**
     * .
     * the method build new Shapes.Line with the given start and end point
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * .
     * the method build new Shapes.Line with the given x1,y1 of the start point and the x2,y2 of the end point
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the start point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * .
     * the method return the length of the line
     *
     * @return length of the line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * .
     * the method returns the middle point of the line
     *
     * @return middle point of the line
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * .
     * the method returns the start point of the line
     *
     * @return start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * .
     * the method returns the end point of the line
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * .
     * the method returns the y-intercept value of the line
     *
     * @return y-intercept value of the line
     */
    public double intercept() {
        // by this calculation: b = y - a*x
        return this.start.getY() - (this.findSlope() * this.start.getX());
    }

    /**
     * .
     * the method returns the slope of the line
     *
     * @return slope of line
     */
    public double findSlope() {
        // by this calculation: a = (y2-y1)/(x2-x1)
        double counter = end.getY() - start.getY();
        double denominator = end.getX() - start.getX();
        return counter / denominator;
    }


    /**
     * .
     * the method check if 2 lines are intersects
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // if one of these functions returns true
        return this.verticalsIsIntersecting(other) || this.horizontalIsIntersecting(other)
                || this.regularIsIntersecting(other);
    }

    /**
     * .
     * the method check if 2 lines are intersects in case one of them or 2 of them vertical
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean verticalsIsIntersecting(Line other) {
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        // check if line 1 is vertical
        if (Compare.doubleEquals(thisStartX, thisEndX)) {
            // if line 2 is also vertical
            if (Compare.doubleEquals(otherStartX, otherEndX)) {
                // if the x is different, so they are parallels and don't intersect
                return Compare.doubleEquals(thisStartX, otherStartX);
            /* only line 1 is vertical so check if the intersection point of
                 the second line are in the range of line 1, and returns true/false in accordance,
                 if they intersect */
            } else {
                double b = other.intercept();
                double intersectionY;
                intersectionY = (other.findSlope() * thisStartX) + b;
                return (Math.min(thisStartY, thisEndY) <= intersectionY)
                        && (intersectionY <= Math.max(thisStartY, thisEndY));
            }
            //if line 2 start where line 1 end
        } else if (Compare.doubleEquals(thisEndX, otherStartX) && Compare.doubleEquals(thisEndY, otherStartY)) {
            return true;
            //if line 1 start where line 2 end
        } else if (Compare.doubleEquals(otherEndX, thisStartX) && Compare.doubleEquals(otherEndY, thisStartY)) {
            return true;
            //if 2 lines ends in the same point
        } else if (Compare.doubleEquals(thisEndX, otherEndX) && Compare.doubleEquals(thisEndY, otherEndY)) {
            return true;
            //if 2 lines starts in the same point
        } else if (Compare.doubleEquals(thisStartX, otherStartX) && Compare.doubleEquals(thisStartY, otherStartY)) {
            return true;
            // only line 2 is vertical, do same check from previous
        } else if (Compare.doubleEquals(otherStartX, otherEndX)) {
            double b = this.intercept();
            double intersectionY;
            intersectionY = (this.findSlope() * otherStartX) + b;
            return (Math.min(otherStartY, otherEndY) <= intersectionY)
                    && (intersectionY <= Math.max(otherStartY, otherEndY));
        }
        return false;
    }

    /**
     * .
     * the method check if 2 lines are intersects in case one of them or 2 of them is horizontal
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean horizontalIsIntersecting(Line other) {
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        // check if line 1 is horizontal
        if (Compare.doubleEquals(thisStartY, thisEndY)) {
            // if line 2 is also horizontal
            if (Compare.doubleEquals(otherStartY, otherEndY)) {
                // if the y value is different, so they are parallels and return false
                return Compare.doubleEquals(thisStartY, otherStartY);
            /* only line 1 is horizontal so check if the intersection point of
                 the second line are in the range of line 1, and returns true/false in accordance,
                 if they intersect */
            } else {
                double b = other.intercept();
                double intersectionX;
                intersectionX = ((thisStartY - b) / (other.findSlope()));
                return (Math.min(thisStartX, thisEndX) <= intersectionX)
                        && (intersectionX <= Math.max(thisStartX, thisEndX));
            }
            //if line 2 start where line 1 end
        } else if (Compare.doubleEquals(thisEndX, otherStartX) && Compare.doubleEquals(thisEndY, otherStartY)) {
            return true;
            //if line 1 start where line 2 end
        } else if (Compare.doubleEquals(otherEndX, thisStartX) && Compare.doubleEquals(otherEndY, thisStartY)) {
            return true;
            //if 2 lines ends in the same point
        } else if (Compare.doubleEquals(thisEndX, otherEndX) && Compare.doubleEquals(thisEndY, otherEndY)) {
            return true;
            //if 2 lines starts in the same point
        } else if (Compare.doubleEquals(thisStartX, otherStartX) && Compare.doubleEquals(thisStartY, otherStartY)) {
            return true;
            // only line 2 is horizontal, do same check from previous
        } else if (Compare.doubleEquals(otherStartY, otherEndY)) {
            double b = this.intercept();
            double intersectionX;
            intersectionX = ((otherStartY - b) / (this.findSlope()));
            return (Math.min(otherStartX, otherEndX) <= intersectionX)
                    && (intersectionX <= Math.max(otherStartX, otherEndX));
        }
        return false;
    }

    /**
     * .
     * the method check if 2 lines are intersects in case 2 of them are not vertical and not horizontal
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean regularIsIntersecting(Line other) {
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        // in case both of them not vertical and horizontal, first, check if they parallel, or they same line
        if (Compare.doubleEquals(this.findSlope(), other.findSlope())) {
            if (Compare.doubleEquals(this.intercept(), other.intercept())) {
                return this.equals(other);
            } else {
                return false;
            }
            //if line 2 start where line 1 end
        } else if (Compare.doubleEquals(thisEndX, otherStartX) && Compare.doubleEquals(thisEndY, otherStartY)) {
            return true;
            //if line 1 start where line 2 end
        } else if (Compare.doubleEquals(otherEndX, thisStartX) && Compare.doubleEquals(otherEndY, thisStartY)) {
            return true;
            //if 2 lines ends in the same point
        } else if (Compare.doubleEquals(thisEndX, otherEndX) && Compare.doubleEquals(thisEndY, otherEndY)) {
            return true;
            //if 2 lines starts in the same point
        } else if (Compare.doubleEquals(thisStartX, otherStartX) && Compare.doubleEquals(thisStartY, otherStartY)) {
            return true;
                /* if they not vertical and not parallels so find
               the intersection x and check if it is in the range of the 2 lines */
        } else {
            double intersectionX = (-(this.intercept() - other.intercept()))
                    / (this.findSlope() - other.findSlope());
            return ((Math.min(thisStartX, thisEndX) <= intersectionX)
                    && (intersectionX <= Math.max(thisStartX, thisEndX)))
                    && (Math.min(otherStartX, otherEndX) <= intersectionX)
                    && (intersectionX <= Math.max(otherStartX, otherEndX));
        }
    }

    /**
     * .
     * the method check if 2 lines that 1 of them or 2 of them are verticals are intersections\
     *
     * @param other line
     * @return intersection point
     */
    public Point intersectionWithVertical(Line other) {
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        double slope1 = this.findSlope();
        double yIntercept1 = this.intercept();
        double slope2 = other.findSlope();
        double yIntercept2 = other.intercept();
        // if the lines intersects
        if (this.isIntersecting(other)) {
            // if line 1 is vertical and line 2 isn't
            if ((Compare.doubleEquals(thisStartX, thisEndX)) && !(Compare.doubleEquals(otherStartX, otherEndX))) {
                double y = (thisStartX * slope2) + yIntercept2;
                Point p = new Point(thisStartX, y);
                // check if the intersectionPoint is inside the ranges of the segment lines
                if (this.inRange(p, other)) {
                    return p;
                }
                // if line 2 is vertical and line 1 isn't
            } else if ((Compare.doubleEquals(otherStartX, otherEndX))
                    && !(Compare.doubleEquals(thisStartX, thisEndX))) {
                double y = (otherStartX * slope1) + yIntercept1;
                Point p = new Point(otherStartX, y);
                if (other.inRange(p, this)) {
                    return p;
                }
                //if line 2 start where line 1 end
            } else if (Compare.doubleEquals(thisEndX, otherStartX) && Compare.doubleEquals(thisEndY, otherStartY)) {
                return new Point(thisEndX, thisEndY);
                //if line 1 start where line 2 end
            } else if (Compare.doubleEquals(otherEndX, thisStartX) && Compare.doubleEquals(otherEndY, thisStartY)) {
                return new Point(otherEndX, otherEndY);
                //if 2 lines ends in the same point
            } else if (Compare.doubleEquals(thisEndX, otherEndX) && Compare.doubleEquals(thisEndY, otherEndY)) {
                return new Point(thisEndX, thisEndY);
                //if 2 lines starts in the same point
            } else if (Compare.doubleEquals(thisStartX, otherStartX) && Compare.doubleEquals(thisStartY, otherStartY)) {
                return new Point(thisStartX, thisStartY);
            }
        }
        return null;
    }

    /**
     * .
     * the method check if 2 lines that 1 of them or 2 of them are horizontals are intersections
     *
     * @param other line
     * @return intersection point
     */
    public Point intersectionWithHorizontal(Line other) {
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        double slope1 = this.findSlope();
        double yIntercept1 = this.intercept();
        double slope2 = other.findSlope();
        double yIntercept2 = other.intercept();
        // if the lines intersects
        if (this.isIntersecting(other)) {
            // if line 1 is horizontal and line 2 isn't
            if ((Compare.doubleEquals(thisStartY, thisEndY)) && !(Compare.doubleEquals(otherStartY, otherEndY))) {
                double x = (thisStartY - yIntercept2) / slope2;
                Point p = new Point(x, thisStartY);
                // if the point is in the range
                if (this.inRange(p, other)) {
                    return p;
                }
                // if line 2 is horizontal and line 1 isn't
            } else if ((Compare.doubleEquals(otherStartY, otherEndY))
                    && !(Compare.doubleEquals(thisStartY, thisEndY))) {
                double x = (otherStartY - yIntercept1) / slope1;
                Point p = new Point(x, otherStartY);
                // if the point is in the range
                if (other.inRange(p, this)) {
                    return p;
                }
                //if line 2 start where line 1 end
            } else if (Compare.doubleEquals(thisEndX, otherStartX) && Compare.doubleEquals(thisEndY, otherStartY)) {
                return new Point(thisEndX, thisEndY);
                //if line 1 start where line 2 end
            } else if (Compare.doubleEquals(otherEndX, thisStartX) && Compare.doubleEquals(otherEndY, thisStartY)) {
                return new Point(otherEndX, otherEndY);
                //if 2 lines ends in the same point
            } else if (Compare.doubleEquals(thisEndX, otherEndX) && Compare.doubleEquals(thisEndY, otherEndY)) {
                return new Point(thisEndX, thisEndY);
                //if 2 lines starts in the same point
            } else if (Compare.doubleEquals(thisStartX, otherStartX) && Compare.doubleEquals(thisStartY, otherStartY)) {
                return new Point(thisStartX, thisStartY);
            }
        }
        return null;
    }

    /**
     * .
     * the method check if 2 regular lines (not vertical or horizontal) are intersections
     *
     * @param other line
     * @return intersection point
     */
    public Point intersectionWithRegular(Line other) {
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        double slope1 = this.findSlope();
        double yIntercept1 = this.intercept();
        double slope2 = other.findSlope();
        double yIntercept2 = other.intercept();
        double x = (yIntercept2 - yIntercept1) / (slope1 - slope2);
        double y = (x * slope1) + yIntercept1;
        if (this.isIntersecting(other)) {
            Point p = new Point(x, y);
            // check if the interception Shapes.Point is in the range of the lines
            if (this.inRange(p, other)) {
                return p;
                //if line 2 start where line 1 end
            } else if (Compare.doubleEquals(thisEndX, otherStartX) && Compare.doubleEquals(thisEndY, otherStartY)) {
                return new Point(thisEndX, thisEndY);
                //if line 1 start where line 2 end
            } else if (Compare.doubleEquals(otherEndX, thisStartX) && Compare.doubleEquals(otherEndY, thisStartY)) {
                return new Point(otherEndX, otherEndY);
                //if 2 lines ends in the same point
            } else if (Compare.doubleEquals(thisEndX, otherEndX) && Compare.doubleEquals(thisEndY, otherEndY)) {
                return new Point(thisEndX, thisEndY);
                //if 2 lines starts in the same point
            } else if (Compare.doubleEquals(thisStartX, otherStartX) && Compare.doubleEquals(thisStartY, otherStartY)) {
                return new Point(thisStartX, thisStartY);
            }
        }
        return null;
    }

    /**
     * the method Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other line
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if the lines are the same line
        if (this.equals(other)) {
            return null;
            // if we got intersection point in any function return it
        } else if (this.intersectionWithVertical(other) != null) {
            return this.intersectionWithVertical(other);
        } else if (this.intersectionWithHorizontal(other) != null) {
            return this.intersectionWithHorizontal(other);
        } else if (this.intersectionWithRegular(other) != null) {
            return this.intersectionWithRegular(other);
        }
        return null;
    }


    /**
     * .
     * the method checks if the intersection point between 2 lines is in the segments of the lines
     * (and not in the infinity)
     *
     * @param intersectionPoint the intersection point between 2 lines
     * @param other             line
     * @return true if the intersection point is in the segments lines
     */
    public boolean inRange(Point intersectionPoint, Line other) {
        double x = intersectionPoint.getX();
        double y = intersectionPoint.getY();
        double thisStartX = this.start.getX();
        double thisEndX = this.end.getX();
        double otherStartX = other.start.getX();
        double otherEndX = other.end.getX();
        double thisStartY = this.start.getY();
        double thisEndY = this.end.getY();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();
        // if the x and y values are in the ranges of the segments lines
        return (Math.min(thisStartX, thisEndX) <= x)
                && (x <= Math.max(thisStartX, thisEndX))
                && (Math.min(otherStartX, otherEndX) <= x)
                && (x <= Math.max(otherStartX, otherEndX)) && ((Math.min(thisStartY, thisEndY) <= y)
                && (y <= Math.max(thisStartY, thisEndY)))
                && (Math.min(otherStartY, otherEndY) <= y)
                && (y <= Math.max(otherStartY, otherEndY));
    }

    /**
     * .
     * the method check if the line are equals
     *
     * @param other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }

    /**
     * .
     * the method return the closest intersection point to the start of the line
     *
     * @param rect rectangle
     * @return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points == null) {
            return null;
        }
        double minDis = 2000000000;
        Point closetPoint = null;
        for (Point point : points) {
            if (point != null) {
                if (this.start().distance(point) < minDis) {
                    minDis = this.start().distance(point);
                    closetPoint = point;
                }
            }
        }
        return closetPoint;
    }

    /**
     * the method check if the collisionPoint is on the line.
     *
     * @param collisionPoint collision point
     * @return true if the collision point is on the line and false if not
     */
    public boolean onTheLine(Point collisionPoint) {
        return ((Math.min(this.start().getX(), this.end().getX()) <= collisionPoint.getX())
                && (collisionPoint.getX() <= Math.max(this.start().getX(), this.end().getX()))
                && (Math.min(this.start().getY(), this.end().getY()) <= collisionPoint.getY())
                && (collisionPoint.getY() <= Math.max(this.start().getY(), this.end().getY())));
    }
}
