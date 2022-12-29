//ID: 316482355

package listeners;

import collidables.Block;
import interfaces.HitListener;
import others.Counter;
import screens.GameLevel;
import sprites.Ball;

/**
 * listeners.BlockRemover - in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    // game - game to remove from the block. blocksLeft - others.Counter of remains blocks.
    private GameLevel gameLevel;
    private Counter blocksLeft;

    /**
     * Constructor. creates a listeners.BlockRemover according to params.
     * @param gameLevel - game to remove blocks from.
     * @param blocksNum - others.Counter of blocks in game (remains).
     */
    public BlockRemover(GameLevel gameLevel, Counter blocksNum) {
        this.gameLevel = gameLevel;
        this.blocksLeft = blocksNum;
    }

    /**
     * method removes block beingHit from game and update the counter.
     * @param beingHit - the block that got hit.
     * @param hitter - the sprites.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        this.blocksLeft.decrease(1);
    }
}