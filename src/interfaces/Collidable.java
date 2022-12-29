// ID: 316482355

package interfaces;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * Collidable - an object shaped as geometry.Rectangle that is collide able.
 */
public interface Collidable {

    /**
     * method returns this Coliidable's geometry.Rectangle.
     * @return geometry.Rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * method gets a collision point with this Collidable and velocity and returns the new Velocity after hit occurred.
     * @param hitter - the ball that hit this Collidable.
     * @param collisionPoint - point of collision with this collidable.
     * @param currentVelocity - velocity of the object hit this collidable.
     * @return the new velocity of the object hit this collidbale, after thi hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
