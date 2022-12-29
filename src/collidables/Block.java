// ID: 316482355

package collidables;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import screens.GameLevel;
import sprites.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block - a Sprite collidable object - implements interfaces.Collidable and interfaces.Sprite.
 * contains Rectangle and a color.
 * can also calculate hit of velocity and returns new velocity accordingly.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    // hitListeners - list of HitListeners to notify to when hit occurred.
    private List<HitListener> hitListeners;
    // rec- a geometry.Rectangle, shape of the block. color - the color.
    private Rectangle rec;
    private java.awt.Color color;

    /**
     * Block constructor. gets a Rectangle and a color and creates a Block.
     * @param rec1 - a rectangle.
     * @param c - color.
     */
    public Block(Rectangle rec1, Color c) {
        this.rec = rec1;
        this.color = c;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * the method returns the Block's Rectangle.
     * @return Block's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * the method gets collision point and a velocity, and returns the new velocity of the object hit this Block.
     * @param hitter - the ball that hit this block.
     * @param collisionPoint - Point of collision.
     * @param currentVelocity - velocity of the object that hit this Block.
     * @return new Velocity for the object that hit this Block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // edges - array size 4 - edges of the rectangle of this collidables.Block.
        // first edge in array is the top of rectangle, second is bottom, third left side, and forth is right side.
        Line[] edges = this.rec.getEdges();
        // dx - dx of currentVelocity, dy - dy of currentVelocity.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if collision point is at top edge (using isOnline) and dy direction face down, changes dy direction opposite.
        if (edges[0].isOnLine(collisionPoint.toInt()) && dy > 0) {
            dy *= -1;
        }
        // if collision point is at bottom edge and dy direction face up, changes dy direction opposite.
        if (edges[1].isOnLine(collisionPoint.toInt()) && dy < 0) {
            dy *= -1;
        }
        // if collision point is at left edge and dx direction face right, changes dx direction opposite.
        if (edges[2].isOnLine(collisionPoint.toInt()) && dx > 0) {
            dx *= -1;
        }
        // if collision point is at right edge and dx direction face left, changes dx direction opposite.
        if (edges[3].isOnLine(collisionPoint.toInt()) && dx < 0) {
            dx *= -1;
        }
        // notify to all HitListeners that hit occurred.
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * the method gets a draw surface and draw this Block on it.
     * @param surface - draw surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        int width = (int) this.rec.getEdges()[0].length();
        int height = (int) this.rec.getEdges()[2].length();
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * an amazing method that does absolutely nothing.
     */
    public void timePassed() {

    }

    /**
     * the method gets a others.Game and add this Block to the game, as a Sprite and as a Collidable.
     * @param g - game to add this block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * the method gets a others.Game and remove this Block from the game, as a Sprite and as a Collidable.
     * @param gameLevel - game to remove this block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
        this.removeHitListener(gameLevel.getBlockRemover());
        this.removeHitListener(gameLevel.getScoreListener());
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * method notify all HitListeners that hit occurred.
     * @param hitter - the ball that got hit with this collidables.Block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
