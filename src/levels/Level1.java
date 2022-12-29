// ID: 316482355
package levels;

import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level1 - first level, difficulty - none.
 */
public class Level1 implements LevelInformation {

    // WIDTH - game board width, HEIGHT - game board height.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // blocks - list of block in level. background - background of level. velocities - list of balls velocities.
    private List<Block> blocks;
    private Block background;
    private List<Velocity> velocities;

    /**
     * Level1 constructor. creates background, blocks and balls velocities.
     */
    public Level1() {
        createBackground();
        createBlocks();
        createBallsVelocities();
    }


    @Override
    public int numberOfBalls() {
        return velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return 12;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    /**
     * method creates level block and add to this level blocks list.
     * single block square shaped.
     */
    public void createBlocks() {
        int blockEdge = 25;
        int xCoordinate = WIDTH / 2 - blockEdge / 2, yCoordinate = HEIGHT / 7;
        this.blocks = new ArrayList<>();
        Rectangle rec = new Rectangle(new Point(xCoordinate, yCoordinate), blockEdge, blockEdge);
        Block block = new Block(rec, Color.RED);
        blocks.add(block);
    }

    /**
     * method creates black background.
     */
    public void createBackground() {
        int scoreBlockHeight = 20;
        Rectangle rec = new Rectangle(new Point(0, scoreBlockHeight), WIDTH + 1, HEIGHT + 1);
        this.background = new Block(rec, Color.BLACK);
    }

    /**
     * method creates ball velocity and add to velocities list.
     */
    public void createBallsVelocities() {
        this.velocities = new ArrayList<>();
        double ballAngle = 0;
        double ballSpeed = 6;
        velocities.add(Velocity.fromAngleAndSpeed(ballAngle, ballSpeed));
    }
}
