// ID: 316482355
package interfaces;

import collidables.Block;
import geometry.Velocity;

import java.util.List;

/**
 * LevelInformation - contains balls number, balls velocities, paddle width, background, blocks, blocks num to remove.
 */
public interface LevelInformation {
    /**
     * method returns number of balls.
     * @return balls number.
     */
    int numberOfBalls();

    /**
     * method returns list of balls velocities.
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * method returns paddle speed.
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * method returns paddle width.
     * @return paddle width.
     */
    int paddleWidth();

    /**
     * method returns the level name. will be displayed at the top of the screen.
     * @return level name.
     */
    String levelName();

    /**
     * returns background (block that is bigger than board).
     * @return background.
     */
    Sprite getBackground();

    /**
     *  The Blocks that make up this level, each block contains its size, color and location.
     * @return list of blocks.
     */
    List<Block> blocks();

    /**
     * blocks number that need to be removed to clear level.
     * @return blocks number that need to be removed.
     */
    int numberOfBlocksToRemove();
}
