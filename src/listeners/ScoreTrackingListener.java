//ID: 316482355

package listeners;

import collidables.Block;
import interfaces.HitListener;
import others.Counter;
import sprites.Ball;

/**
 * listeners.ScoreTrackingListener - interfaces.HitListener that listen to hit and update others.Counter of score.
 */
public class ScoreTrackingListener implements HitListener {

    // currentScore - others.Counter of score.
    private Counter currentScore;

    /**
     * Constructor. gets a others.Counter for score and creates listeners.ScoreTrackingListener.
     * @param scoreCounter - counter for score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * method update score others.Counter - gain 5 points for a hit.
     * @param beingHit - the block that got hit.
     * @param hitter - the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
