// ID: 316482355

package geometry;

import java.util.List;
import java.util.ArrayList;

/**
 * Rectangle - contains an upper left point, width and height. and also rest of points and edges.
 * can find intersection points with a line.
 */
public class Rectangle {

    // upperLeft - up left corner of rectangle. width - the width of rectangle. height - height of rectangle.
    private Point upperLeft;
    private double width;
    private double height;
    // upperRight - up right corner of rectangle. lowerLeft - lower left corner of rectangle. lower right - same right.
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    // first edge is top of rectangle, second is bottom, third is left side and forth is right side.
    private Line[] edges = new Line[4];

    /**
     * method creates a new rectangle with requested upper left point, width and height.
     * @param upperLeft - up left corner of rectangle.
     * @param width - rectangle's width.
     * @param height - rectangle height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        completeRecInfo();
    }

    /**
     * the method use 3 main parameters - upperLeft point, width and height, and calculates rest of points and edges.
     */
    public void completeRecInfo() {
        // creates 3 more Points of the geometry.Rectangle.
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        // bottom is higher
        this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.lowerRight = new Point(lowerLeft.getX() + width, lowerLeft.getY());
        // creates 4 lines as 4 edges of geometry.Rectangle.
        this.edges[0] = new Line(this.upperLeft, upperRight);
        this.edges[1] = new Line(lowerLeft, lowerRight);
        this.edges[2] = new Line(this.upperLeft, lowerLeft);
        this.edges[3] = new Line(upperRight, lowerRight);
    }

    /**
     * the method gets a line and returns an array list of intersection points of the line with this rectangle.
     * @param line - line to find intersection with.
     * @return an array list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // creates 4 intersection points of line with the geometry.Rectangle's edges.
        // really can be only 2 points max (when other 2 equal to others)
        Point[] intersections = new Point[4];
        // creates a list and insert the intersection points into.
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < intersections.length; i++) {
            intersections[i] = line.intersectionWith(edges[i]);
            if (intersections[i] != null) {
                list.add(intersections[i]);
            }
        }
        return list;
    }

    /**
     * the method returns rectangle's width.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * the method returns rectangle's height.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the method returns the upper left corner of rectangle.
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * the method returns an array size 4 of edges of rectangle (as 4 lines).
     * first in the array is the top of the rectangle, second is the bottom, third left side, and forth right side.
     * @return an array of rectangles edges.
     */
    public Line[] getEdges() {
        return this.edges;
    }
}