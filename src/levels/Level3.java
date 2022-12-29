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
 * Level3 - third level, difficulty - medium.
 */
public class Level3 implements LevelInformation {

    // WIDTH - game board width, HEIGHT - game board height.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // blocks - list of block in level. background - background of level. velocities - list of balls velocities.
    private List<Block> blocks;
    private Block background;
    private List<Velocity> velocities;

    /**
     * Level3 constructor. creates background, blocks and balls velocities.
     */
    public Level3() {
        createBackground();
        createBlocks();
        createBallsVelocities();
    }

    @Override
    public int numberOfBalls() {
        return 2;
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
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
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
        return this.blocks.size();
    }

    /**
     * method creates level blocks and add to this level blocks list.
     * blocks arranged in 5 rows, stairs.
     */
    public void createBlocks() {
        this.blocks = new ArrayList<>();
        int rowNum = 5, borderWidth = 10, blockWidth = 55, blockHeight = 25;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < rowNum * 2 - i; j++) {
                // start point of each block. height calculated according to board height.
                Point p = new Point(WIDTH - borderWidth - blockWidth * (j + 1), HEIGHT / rowNum + blockHeight * i);
                Rectangle rec = new Rectangle(p, blockWidth, blockHeight);
                // choose color, so every row has same color.
                Block block = new Block(rec, colorChoose(i));
                this.blocks.add(block);
            }
        }
    }

    /**
     * method creates green background.
     */
    public void createBackground() {
        int scoreBlockHeight = 20;
        Rectangle rec = new Rectangle(new Point(0, scoreBlockHeight), WIDTH + 1, HEIGHT + 1);
        this.background = new Block(rec, Color.green);
    }

    /**
     * method creates 2 balls velocities and add to velocities list.
     */
    public void createBallsVelocities() {
        this.velocities = new ArrayList<>();
        double ballsSpeed = 4;
        Velocity v1 = Velocity.fromAngleAndSpeed(Math.toRadians(330), ballsSpeed);
        Velocity v2 = Velocity.fromAngleAndSpeed(Math.toRadians(30), ballsSpeed);
        velocities.add(v1);
        velocities.add(v2);
    }

    /**
     * method gets a number and choose color using the remainder of the number from 6.
     * @param n - number for pick a color.
     * @return a color.
     */
    private Color colorChoose(int n) {
        int remainder = n % 6;
        switch (remainder) {
            case 0:
                return Color.gray;
            case 1:
                return Color.red;
            case 2:
                return Color.yellow;
            case 3:
                return Color.blue;
            case 4:
                return Color.pink;
            default:
                return Color.green;
        }
    }
}
