// ID: 316482355

package interfaces;

import collidables.Block;
import sprites.Ball;

/**
 * HitListener - listen when hit occur, and act appropriate.
 */
public interface HitListener {

    /**
     * method is called whenever the beingHit object is hit.
     * @param beingHit - the block that got hit.
     * @param hitter - the sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
