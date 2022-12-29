//ID: 316482355

package listeners;

import collidables.Block;
import interfaces.HitListener;
import others.Counter;
import screens.GameLevel;
import sprites.Ball;

/**
 * listeners.BallRemover - in charge of removing balls from the game,
 * as well as keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {

    // game - game to remove from the ball. BallsLeft - others.Counter of remains balls.
    private GameLevel gameLevel;
    private Counter ballsLeft;

    /**
     * Constructor. creates a listeners.BallRemover according to params.
     * @param gameLevel - game to remove balls from.
     * @param ballsNum - others.Counter of balls in game (remains).
     */
    public BallRemover(GameLevel gameLevel, Counter ballsNum) {
        this.gameLevel = gameLevel;
        this.ballsLeft = ballsNum;
    }

    /**
     * method removes ball hitter from game and update the counter.
     * @param beingHit - the block that got hit.
     * @param hitter - the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.ballsLeft.decrease(1);
    }
}
