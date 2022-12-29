// ID: 316482355

package geometry;

/**
 * geometry.Point - contains 'x' and 'y' coordinates.
 */
public class Point {
    // x, y - 'x' and 'y' coordinates of the geometry.Point.
    private double x, y;

    /**
     * geometry.Point constructor.
     * creates a new geometry.Point with x and y coordinates.
     * @param x - double number, x coordinate of the point.
     * @param y - double number, y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - calculates the distance between this point and other geometry.Point.
     * @param other - a point.
     * @return double - distance between other point and this point.
     */
    public double distance(Point other) {
        // the distance between points 'x' coordinates, and the distance between points 'y' coordinates.
        double xDistance = other.getX() - this.x;
        double yDistance = other.getY() - this.y;
        // every distance is squared (always positive).
        xDistance *= xDistance;
        yDistance *= yDistance;
        // the distance is sqrt of those 2 squared distances.
        return Math.sqrt(xDistance + yDistance);
    }

    /**
     * equals - compare this point and other point (x, y coordinates) and check if equal.
     * @param other - a geometry.Point.
     * @return boolean - true if the Points are equal, else false.
     */
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }

    /**
     * getX -  returns this point 'x' coordinate.
     * @return double - this point 'x' coordinate.
     */
    public double getX() {
        return this.x;
    }
    /**
     * getY -  returns this point 'y' coordinate.
     * @return double - this point 'y' coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * the method returns approximate int values of this points.
     * @return new point with int coordinates.
     */
    public Point toInt() {
        return new Point(Math.round(this.x), Math.round(this.y));
    }
}
