// ID: 316482355

package geometry;

import java.util.List;

/**
 * Line - contains 2 Points - start and end.
 * The class supports basic line operations as length, middle geometry.Point, and intersections with other Lines.
 */
public class Line {
    // start, end - 2 Points, the edges of the line.
    private Point start, end;
    //  EPSILON - tiny number for accurate equalization.
    private static final double EPSILON = 0.000000000001;

    /**
     * geometry.Line constructor - by 2 points.
     * creates a new geometry.Line between start point and end point..
     * @param start - first edge (point) of the line.
     * @param end - end edge (point) of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * geometry.Line constructor - by 4 coordinates.
     * creates a new geometry.Line between 2 points.
     * @param x1 - 'x' coordinate of first point (one edge of line).
     * @param y1  - 'y' coordinate of first point (one edge of line).
     * @param x2 - 'x' coordinate of second point (last edge of line).
     * @param y2  - 'y' coordinate of second point (last edge of line).
     */
    public Line(double x1, double y1, double x2, double y2) {
        // creates 2 points by those 4 coordinates.
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length of the geometry.Line.
     * @return the distance between start and end points.
     */
    public double length() {
        // the length of the line is the distance between its both points.
        return start.distance(end);
    }

    /**
     * the middle Point of the line.
     * @return the middle geometry.Point of the line.
     */
    public Point middle() {
        // x, y - the 'x' and 'y' coordinates of the middle point.
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * the start geometry.Point of the line.
     * @return start point of this line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * the end Point of the line.
     * @return end point of this line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting checks if this line intersects other line.
     * @param other is a line.
     * @return true if this line intersects other line, else false.
     */
    public boolean isIntersecting(Line other) {
        // using the method intersectionWith, that returns null if doesn't find intersection point.
        return (intersectionWith(other) != null);
    }

    /**
     * intersectionWith returns the intersection with other line, if exists.
     * the method uses the equation of an align (point-slope form): y = m(x - Px) + Py.
     * comparing both y's of aligns, m1(x-Px1)+Py1 = m2(x-Px2)+Py2. finding from here the x.
     * @param other is a line.
     * @return p, the intersection point between other line and this line. if doesn't exists, return null.
     */
    public Point intersectionWith(Line other) {
        // m1 - this line's slope. m2 - other line's slope.
        // mx1, mx2 are temps for the final calculation.
        // x, y - the intersection point coordinates.
        double m1, m2, mx1, mx2, x, y;
        // case this form is x = a
        if (this.start.getX() == this.end.getX()) {
            // case both form x = a
            if (other.start.getX() == other.end.getX()) {
               return this.intersectCase2(other);
            }
            return other.intersectCase1(this);
        }
        // case other form is x = a
        if (other.start.getX() == other.end.getX()) {
            return this.intersectCase1(other);
        }
        m1 = this.getSlope();
        m2 = other.getSlope();
        // if the slope is equal, the lines are on the same align, or parallel. so no intersection point.
        // check if smaller then epsilon for round mistake.
        if (m1 == m2) {
            return this.intersectCase3(other);
        }
        // using the equation, and transporting wings,  mx1 is -m*Px + Py. mx2 is m*Px - Py.
        mx1 = m1 * this.start.getX() - this.start.getY();
        mx2 = other.start().getY() - m2 * other.start().getX();
        // according to the equation, x equals to this formula.
        x = (mx1 + mx2) / (m1 - m2);
        // putting x in the equation gives us y.
        y = m1 * (x - this.start.getX()) + this.start.getY();
        // creates a point with x,y coordinates.
        Point p = new Point(x, y);
        // p is the intersection of 2 aligns each are the expanding of our lines to infinity.
        // check if p is on both lines, and not only on their infinite align.
        if (isOnLine(p) && other.isOnLine(p)) {
            return p;
        } else {
            return null;
        }
    }

    /**
     * equals returns whether this line equals to other line.
     * @param other is a line.
     * @return true if this line equals other line, else false.
     */
    public boolean equals(Line other) {
        return (this.start == other.start() && this.end == other.end());
    }

    /**
     * getSlope return the slope of the line.
     * calculated the way the first point is with the smaller 'x' coordinate, even when 'end' is the first point.
     * @return double - the slope of the line.
     */
    private double getSlope() {
        // xDis - the distance between x coordinates of both line's points.
        // yDis- the distance between x coordinates of both line's points.
        double xDis, yDis;
        // it doesn't matter which point is called 'start' and which 'end'.
        // the slope is calculated in a way that the point with the smaller 'x' is the first one.
        if (this.end.getX() >= this.start.getX()) {
            xDis = this.end.getX() - this.start.getX();
            yDis = this.end.getY() - this.start.getY();
        } else {
            xDis = this.start.getX() - this.end.getX();
            yDis = this.start.getY() - this.end.getY();
        }
        // slope is the distance between 'y' coordinate divided by the distance between 'x' coordinates.
        return yDis / xDis;
    }

    /**
     * isOnLine check whether the point p is on this line.
     * the method consider that p is on the same infinite line which this line is a part of.
     * or this line is parallel to 'x' or 'y' axes.
     * @param p - is a point on the infinite line which this line is part of.
     * @return true if p is also on this line, else false;
     */
    public boolean isOnLine(Point p) {
        // x,y - the coordinates of p.
        double x = p.getX(), y = p.getY();
        // this line is a part of endless align. based on that p is on the align.
        // p is on this line (and not only on the align), only if the x and y are between the start and end points.
        // using epsilon for equalization instead of regular equalization for extra accurate.
        return (x <= Math.max(this.start.getX(), this.end.getX()) + EPSILON
                && x >= Math.min(this.start.getX(), this.end.getX()) - EPSILON
                && y <= Math.max(this.start.getY(), this.end.getY()) + EPSILON
                && y >= Math.min(this.start.getY(), this.end.getY()) - EPSILON);
    }

    /**
     * find and return the intersection point of this and other line.
     * referring to the specific case when this line has regular slope, but other line is in form 'x = a'.
     * @param other - a line in form 'x = a'.
     * @return p - intersection point of this and other lines if exists. else return null.
     */
    private Point intersectCase1(Line other) {
        // m, x, px, py, y - all parts of the equation "y = m(x - px) + py
        // m - this slope, x - other line's x (same x whole line), px and py - start point's coordinates of this line.
        // y is the 'y' coordinate of the intersection point - p.
        double m = this.getSlope();
        double x = other.start.getX();
        double px = this.start.getX();
        double py = this.start.getY();
        double y = m * (x - px) + py;
        Point p = new Point(x, y);
        if (this.isOnLine(p) && other.isOnLine(p)) {
            return p;
        }
        return null;
    }

    /**
     * find and return the intersection point of this and other line.
     * referring to the specific case when both lines are in form 'x = a'.
     * @param other - a line in form 'x = a'.
     * @return p - intersection point of this and other lines if exists. else return null.
     */
    private Point intersectCase2(Line other) {
        // intersection point is possible only if both lines have same 'x' in start point (or end point).
        if (this.start.getX() == other.start.getX()) {
            // thisMaxY - the higher 'y' value between both points of this line.
            // finds as well the lower point's 'y' of this line, and the same for other line.
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            double thisMinY =  Math.min(this.start.getY(), this.end.getY());
            double otherMaxY = Math.max(other.start.getY(), other.end.getY());
            double otherMinY = Math.min(other.start.getY(), other.end.getY());
            // intersection point exists only if the highest point of one line is the lowest of the other.
            if (thisMaxY == otherMinY) {
                return new Point(this.start.getX(), thisMaxY);
            }
            if (thisMinY == otherMaxY) {
                return new Point(this.start.getX(), thisMinY);
            }
        }
        return null;
    }

    /**
     * find and return the intersection point of this and other line.
     * referring to the specific case when both lines have same slope.
     * @param other - a line that has same slope as this line.
     * @return p - intersection point of this and other lines if exists. else return null.
     */
    private Point intersectCase3(Line other) {
        // intersection point exists only if one line starts same point where the other one ends.
        if (this.start.equals(other.start)) {
            // check that the 'x' of that point is the lowest 'x' for one line and the highest 'x' for the other.
            if ((this.end.getX() >= this.start.getX() && other.end.getX() <= other.start.getX())
                    || (this.end.getX() <= this.start.getX() && other.end.getX() >= other.start.getX())) {
                return this.start;
            }
        } else if (this.start.equals(other.end)) {
            if ((this.end.getX() >= this.start.getX() && other.start.getX() <= other.end.getX())
                    || (this.end.getX() <= this.start.getX() && other.start.getX() >= other.end.getX())) {
                return this.start;
            }
        } else if (this.end.equals(other.start)) {
            if ((this.start.getX() >= this.end.getX() && other.end.getX() <= other.start.getX())
                    || (this.start.getX() <= this.end.getX() && other.end.getX() >= other.start.getX())) {
                return this.end;
            }
        } else if (this.end.equals(other.end)) {
            if ((this.start.getX() >= this.end.getX() && other.start.getX() <= other.end.getX())
                    || (this.start.getX() <= this.end.getX() && other.start.getX() >= other.end.getX())) {
                return this.end;
            }
        }
        return null;
    }

    /**
     * the method gets a Rectangle and returns the intersection point of this line with the Rectangle which closest to
     * start of line. if there are no intersection points returns null.
     * @param rect - a Rectangle to find intersection with.
     * @return a point - the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // intersectionPoints - array list of all intersection points of this line with rect.
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        // minDis - the distance of requested point from start of line. closetP - the closest point found so far.
        double minDis = this.length() + 1;
        Point closestP = null;
        // loop find from all intersection points in list the closest one.
        for (Point p : intersectionPoints) {
            if (p.distance(this.start) < minDis) {
                minDis = p.distance(this.start);
                closestP = p;
            }
        }
        return closestP;
    }
}