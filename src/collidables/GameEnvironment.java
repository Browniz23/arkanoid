// ID: 316482355

package collidables;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import others.CollisionInfo;

import java.util.List;
import java.util.ArrayList;

/**
 * GameEnvironment - a list of collidable items.
 * also can find first collision with a requested line.
 */
public class GameEnvironment {

    // list of collidable items.
    private List<Collidable> collidables;

    /**
     * constructor - creates a new GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * the method add c to the collidbles list.
     * @param c - collidable item.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * the method find the first collision point from the start of the line,
     * between the line and the items in collidables.
     * @param trajectory - a line.
     * @return collision info - contains collision point and collided object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // minDis - distance between start of trajectory and collision point. the smallest between al collideables.
        double minDis = trajectory.length() + 1;
        // closestP - closest collision point to start of trajectory. closest - closest collidable item.
        Point closestP = null;
        Collidable closest = null;
        // loop runs over collidables and finds the closest intersection point and the appropriate colliable.
        List<Collidable> tempCollidables = new ArrayList<>(this.collidables);
        for (Collidable c : tempCollidables) {
            // find the closest point to specific collideable c.
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                double currDis = trajectory.start().distance(p);
                if (currDis < minDis) {
                    minDis = currDis;
                    closest = c;
                    closestP = p;
                }
            }
        }
        // means no intersection points.
        if (closestP == null) {
            return null;
        }
        // returns collision - Collision information includes point and the collidable item.
        CollisionInfo collision = new CollisionInfo(closestP, closest);
        return collision;
    }

    /**
     * the method return the collidables list.
     * @return this.collidables.
     */
    public List<Collidable> getCollideables() {
        return this.collidables;
    }
}
