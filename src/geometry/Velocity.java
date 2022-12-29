// ID: 316482355

package geometry;

/**
 * geometry.Velocity - specifies the change in position on the 'x' and the 'y' axes.
 * the formal form is "dx and dy" - the distance in both axes.
 * though velocity can be created also by "speed and angle" - uses as vector - power and direction.
 **/

public class Velocity {

    // dx - the distance in 'x' axe. dy - the distance in 'y' axe.
    private double dx, dy;

    /**
     * velocity constructor.
     * gets 2 double numbers and creates new velocity of dx and dy.
     * @param dx - the distance in 'x' axe.
     * @param dy - the distance in 'y' axe.
     */
    public Velocity(double dx, double dy) {
        this .dx = dx;
        this.dy = dy;
    }

    /**
     * velocity semi-constructor.
     * the method gets an angle and speed, and convert those into "dx and dy" form using trigonometry.
     * consider that 'y' axe faces down.
     * @param angle - the direction of velocity **in radians**. '0' means up and PI/2 means right.
     * @param speed - the total distance of one step.
     * @return a new geometry.Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // dx - the distance only in axe 'x'. dy - the distance only in axe 'y'.
        // there is a minus in dy because our axes system faces down.
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * the method returns 'x' distance of this velocity.
     * @return this dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * the method returns 'y' distance of this velocity.
     * @return this dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the method gets a starting point and returns a new point we get by this speed.
     * means this velocity brings us from starting point to the new point returned.
     * @param p - a start point.
     * @return new point we got from start point and this speed.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * the method returns the total speed of the velocity. using dx, dy and pythagoras sentence.
     * @return the total speed of the velocity.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

    /**
     * the method returns the angle of the velocity. using opposite dy, so y axe faces down.
     * @return the angel of the velocity.
     */
    public double getAngle() {
        return Math.atan(this.dx / -this.dy);
    }
}