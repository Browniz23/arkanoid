// ID: 316482355

package others;

import geometry.Point;
import interfaces.Collidable;

/**
 * CollisionInfo - information of collision. includes collision point and collidable object.
 */
public class CollisionInfo {

    // collidObject - the collidable object. collisionPoint - the point of collision.
    private Collidable collidObject;
    private Point collisionPoint;

    /**
     * constructor. gets point and collidable and creates a new others.CollisionInfo.
     * @param p - collision point.
     * @param c - collidable object.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collidObject = c;
        this.collisionPoint = p;
    }

    /**
     * the method returns collision point.
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the method returns the collidable object.
     * @return collidable object.
     */
    public Collidable collisionObject() {
        return this.collidObject;
    }

}
