// ID: 316482355

package sprites;

import biuoop.DrawSurface;
import collidables.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;
import others.CollisionInfo;
import screens.GameLevel;

import java.awt.Color;

/**
 * Ball - contains a center Point, integer radius, color (from the java.awt.Color class), and velocity.
 * the velocity of the ball contains the distance between center point and the new point after one step of the ball.
 */
public class Ball implements Sprite {
    // center - the center geometry.Point of the ball. r - the radius, color - ball's color, v - velocity of the ball.
    // gameE - list of collidable objects this ball can collide with.
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameE;

    /**
     * sprites.Ball constructor.
     * gets a center point, radius, color and game environment and creates a new ball with those parameters,
     * with no velocity as default.
     * @param center - the center point of the ball.
     * @param r - the integer radius.
     * @param color - color of the ball.
     * @param g - the game environment. contains a list of all collidable objects the ball can hit.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.v = null;
        this.gameE = g;
    }

    /**
     * sprites.Ball constructor.
     * gets a center point, radius, color and creates a new ball with those parameters, with no velocity as default.
     * @param center - the center point of the ball.
     * @param r      - the integer radius.
     * @param color  - color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.v = null;
        this.gameE = null;
    }

    /**
     * sprites.Ball constructor.
     * gets a center point, radius, color, velocity and creates a new ball with those parameters.
     * @param center - the center point of the ball.
     * @param r      - the integer radius.
     * @param color  - color of the ball.
     * @param v      - the velocity of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity v) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.v = v;
    }

    /**
     * sprites.Ball constructor.
     * gets 2 double numbers as coordinates of the center of the ball, radius, and color.
     * creates a new ball with those parameters, with no velocity as default.
     * @param x     - 'x' coordinate of the center point of the ball.
     * @param y     - 'y' coordinate of the center point of the ball.
     * @param r     - the integer radius.
     * @param color - color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * the method returns the 'x' coordinate of the center point of the ball.
     * @return int - 'x' coordinate of the center point.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * the method returns the 'y' coordinate of the center point of the ball.
     * @return int - 'y' coordinate of the center point.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * the method returns the size of the ball, means its radius.
     * @return radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * the method returns the color of the ball.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * the method gets a draw surface and draw this ball on it.
     * @param surface - draw surface to drawn on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), getSize());
    }

    /**
     * the method gets a velocity and update this ball's velocity.
     * @param v1 - the velocity.
     */
    public void setVelocity(Velocity v1) {
        this.v = v1;
    }

    /**
     * the method creates new velocity from 2 double numbers, and update this ball's velocity.
     * @param dx - distance the ball moves in 'x' - horizontal.
     * @param dy - distance the ball moves in 'y' - vertical.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * the method returns this ball's velocity.
     * @return this ball's velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * the method gets a GameEnvironment and insert to this.gameE. contains list of collidable objects for the ball.
     * @param g - GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameE = g;
    }

    /**
     * the method returns this ball's game environment.
     * @return this ball's game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameE;
    }

    /**
     * the method update the center of the ball after one step according to ball's velocity.
     * the method also update the ball's velocity in case the ball hit a collidable object.
     */
    public void moveOneStep() {
        if (this.v != null) {
            if (this.gameE != null) {
                //this.center = this.center.toInt(); //TODO change to check velocity damage
                // endP - the point of the ball if he complete full step, as int.
                Point endP = this.v.applyToPoint(this.center).toInt();
                // traj - ball's trajectory. line from center of ball to endP. both int coordinate points.
                Line traj = new Line(this.center, endP);
                // cInf - collision info of the intersection between traj and collidable objects in gameE.
                CollisionInfo cInf = this.gameE.getClosestCollision(traj);
                if (cInf == null) {
                    // returns new point, the target of one step move, from ball's center point, according to its speed.
                    this.center = this.getVelocity().applyToPoint(this.center);
                } else {
                    // distance - distance between center of ball to collision point.
                    double distance = this.center.distance(cInf.collisionPoint());
                    // to prevent stuck of the ball, divide between times distance is bigger than radius+1 and other.
                    if (distance >  this.r + 2) {
                        // tempV - velocity to close point before collision point. update ball's center this place.
                        Velocity tempV = Velocity.fromAngleAndSpeed(this.v.getAngle(), distance - this.r);
                        this.center = tempV.applyToPoint(this.center);
                        // else calculate the speed of the new velocity different, as distance/10, only if distance > 1.
                    }  else if (distance > 2 + distance / 10) {
                        Velocity tempV = Velocity.fromAngleAndSpeed(this.v.getAngle(), distance / 10);
                        this.center = tempV.applyToPoint(this.center);
                    }
                    // change velocity of the ball according to kind of collision.
                    setVelocity(cInf.collisionObject().hit(this, cInf.collisionPoint(), this.v));
                }
            } else {
                // update ball's center. the target of one step move, from ball's center point, according to its speed.
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        }
    }

    /**
     * the method moves the ball one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the method gets a others.Game and adds this ball to it. as a interfaces.Sprite.
     * @param g - a others.Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * the method gets a others Game and remove this sprites Ball from the game.
     * @param gameLevel - game to remove this ball from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}