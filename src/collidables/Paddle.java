// ID: 316482355

package collidables;

import  biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import screens.GameLevel;
import sprites.Ball;

import java.awt.Color;

/**
 * Paddle - a Sprite Collidable object. implements Sprite and Collidable. A collidable Rectangle that moves to left and
 * right according to user keyboard.
 * can also calculate hit of velocity and returns new velocity accordingly.
 */
public class Paddle implements Sprite, Collidable {

    // keyboard - keyboard sensor to move paddle. color - paddle color. rec - the geometry.Rectangle of the paddle.
    // leftBorder - most left 'x' coordinate the paddle can reach.
    // RightBorder - most right 'x' coordinate the paddle can reach.
    // step - one step move of the paddle, use as paddle speed.
    private KeyboardSensor keyboard;
    private Color color;
    private Rectangle rec;
    private int leftBorder;
    private int rightBorder;
    private int step;

    /**
     * Paddle constructor. gets rectangle, color, gui screen, and borders for paddle movement. creates a paddle.
     * @param rec - paddle's rectangle.
     * @param color - paddle's color.
     * @param gui - the screen paddle's shows in.
     * @param leftBorder - left border for paddle movement.
     * @param rightBorder - right border for paddle movement.
     * @param speed - speed of paddle (step).
     */
    public Paddle(Rectangle rec, Color color, GUI gui, int leftBorder, int rightBorder, int speed) {
        this.rec = rec;
        this.keyboard = gui.getKeyboardSensor();
        this.color = color;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.step = speed;
    }

    /**
     * the method moves paddle one step to the left.
     */
    public void moveLeft() {
        // if after the step paddle didnt reach border, makes a full step to the left.
        if (this.rec.getUpperLeft().getX() - this.step > leftBorder) {
            Point newP = new Point(this.rec.getUpperLeft().getX() - this.step, this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newP, rec.getWidth(), rec.getHeight());
            // if the step reach to the border, make a step to the end.
        } else {
            Point newP = new Point(leftBorder, this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newP, rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * the method moves paddle one step to the right.
     */
    public void moveRight() {
        // if after the step paddle didnt reach border, makes a full step to the right.
        if (this.rec.getUpperLeft().getX() + rec.getWidth() < rightBorder) {
            Point newP = new Point(rec.getUpperLeft().getX() + this.step, rec.getUpperLeft().getY());
            this.rec = new Rectangle(newP, rec.getWidth(), rec.getHeight());
            // if the step reach to the border, make a step to the end.
        } else {
            Point newP = new Point(rightBorder - rec.getWidth(), this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newP, rec.getWidth(), rec.getHeight());
        }
    }

    /**
     * method checked if a left key or right key in keyboard is pressed, and make a step accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * the method gets a draw surface and draw this Paddle on it.
     * @param d - draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        int width = (int) this.rec.getEdges()[0].length();
        int height = (int) this.rec.getEdges()[2].length();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * method returns Paddle's rectangle.
     * @return this paddle's rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * method gets collision point and current velocity, and returns the new geometry.Velocity calculated this way:
     * 5 equal regions of top of the paddle. most left returns the speed in angle 60 to left. second returns speed
     * in angle 30 to left. third returns velocity after regular hit, forth 30 to right, and fifth 60 to right.
     * @param hitter - the ball that hit this Paddle.
     * @param collisionPoint - point of collision with this collidable.
     * @param currentVelocity - velocity of the object hit this collidable.
     * @return a new geometry.Velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // edges - edges of the rectangle of this paddle.
        // regionSize - size of each region - width.
        Line[] edges = this.rec.getEdges();
        double regionSize = rec.getWidth() / 5;
        // dx - current velocity in 'x' axe. dy - current velocity in 'y' axe.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if the hit occurred at top of paddle, check region and returns appropriate speed.
        if (edges[0].isOnLine(collisionPoint.toInt()) && dy > 0) {
            // region 1
            if (rec.getUpperLeft().getX() + regionSize > collisionPoint.getX()) {
                return regionCalculate(300, currentVelocity);
            }
            // region 2
            if (rec.getUpperLeft().getX() + 2 * regionSize > collisionPoint.getX()) {
                 return regionCalculate(330, currentVelocity);
            }
            // region 3
            if (rec.getUpperLeft().getX() + 3 * regionSize > collisionPoint.getX()) {
                return new Velocity(dx, -dy);
            }
            // region 4
            if (rec.getUpperLeft().getX() + 4 * regionSize > collisionPoint.getX()) {
                return regionCalculate(30, currentVelocity);
            } else { // region 5
                return regionCalculate(60, currentVelocity);
            }
            // case hit other edges.
        } else {
            return regularHit(collisionPoint, currentVelocity);
        }
    }

    /**
     * method gets an angle in degrees and current velocity and return new velocity.
     * @param angle - new angle in degrees.
     * @param currentVelocity - current geometry.Velocity.
     * @return new geometry.Velocity after hit.
     */
    public Velocity regionCalculate(int angle, Velocity currentVelocity) {
        return Velocity.fromAngleAndSpeed(Math.toRadians(angle), currentVelocity.getSpeed());
    }

    /**
     * the method gets collision point and a velocity, and returns the new velocity of the object hit this Block.
     * doesnt include top edge of paddle.
     * @param collisionPoint - geometry.Point of collision.
     * @param currentVelocity - velocity of the object that hit this Block.
     * @return new geometry.Velocity for the object that hit this Block.
     */
    public Velocity regularHit(Point collisionPoint, Velocity currentVelocity) {
        // edges - array size 4 - edges of the rectangle of this Block.
        // first edge in array is the top of rectangle, second is bottom, third left side, and forth is right side.
        Line[] edges = this.rec.getEdges();
        // dx - dx of currentVelocity, dy - dy of currentVelocity.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
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
        return new Velocity(dx, dy);
    }

    /**
     * the method gets a Game and add this Paddle to the game, as a interfaces.Sprite and as a Collidable.
     * @param g - game to add this paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
