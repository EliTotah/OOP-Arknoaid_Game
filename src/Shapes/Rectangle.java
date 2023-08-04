package Shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * the class represent rectangle.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * the method Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line line
     * @return List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<Point>();
        Point[] corners = rectCorners();
        Line[] rectLines = rectLines(corners);
        for (int i = 0; i < rectLines.length; i++) {
            Point point = rectLines[i].intersectionWith(line);
            if ((point != null) && (!(intersectionPoints.contains(point))) && rectLines[i].inRange(point, line)) {
                intersectionPoints.add(point);
            }
        }
        return intersectionPoints;
    }

    /**
     * the method return the rectangle corners.
     *
     * @return the rectangle corners
     */
    public Point[] rectCorners() {
        Point[] corners = new Point[4];
        //upperLeft
        corners[0] = this.getUpperLeft();
        //upperRight
        corners[1] = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        //bottomRight
        corners[2] = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        //bottomLeft
        corners[3] = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        return corners;
    }

    /**
     * the method create the rectangle lines.
     *
     * @param corners the corners of the rectangle
     * @return the rectangle lines
     */
    public Line[] rectLines(Point[] corners) {
        Line[] rectLines = new Line[4];
        //upperLine
        rectLines[0] = new Line(corners[0], corners[1]);
        //rightLine
        rectLines[1] = new Line(corners[1], corners[2]);
        //bottomLine
        rectLines[2] = new Line(corners[2], corners[3]);
        //leftLine
        rectLines[3] = new Line(corners[3], corners[0]);
        return rectLines;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}

