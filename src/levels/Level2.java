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
 * Level2 - second level, difficulty - easy.
 */
public class Level2 implements LevelInformation {

    // WIDTH - game board width, HEIGHT - game board height.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // blocks - list of block in level. background - background of level. velocities - list of balls velocities.
    private List<Block> blocks;
    private Block background;
    private List<Velocity> velocities;

    /**
     * Level2 constructor. creates background, blocks and balls velocities.
     */
    public Level2() {
        createBackground();
        createBlocks();
        createBallsVelocities();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 700;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
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
     * blocks arranged in 1 rows, for all width.
     */
    public void createBlocks() {
        this.blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int borderLength = 10, blocksNum = 15, blockWidth = (WIDTH - 2 * borderLength) / blocksNum;
            int  blockHeight = 25, xCoordinate = borderLength + i * blockWidth, yCoordinate = HEIGHT / 3;
            // borders width are 10.
            Rectangle rec = new Rectangle(new Point(xCoordinate, yCoordinate), blockWidth, blockHeight);
            Block block = new Block(rec, this.colorChoose(i));
            blocks.add(block);
        }
    }

    /**
     * method creates white background.
     */
    public void createBackground() {
        int scoreBlockHeight = 20;
        Rectangle rec = new Rectangle(new Point(0, scoreBlockHeight), WIDTH + 1, HEIGHT + 1);
        this.background = new Block(rec, Color.white);
    }

    /**
     * method creates 10 balls velocities and add to velocities list.
     */
    public void createBallsVelocities() {
        int firstAngle = -50, lastAngle = 50, anglesDis = 10;
        this.velocities = new ArrayList<>();
        double ballsSpeed = 6;
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(Math.toRadians(firstAngle + i * anglesDis), ballsSpeed);
            this.velocities.add(v);
        }
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(Math.toRadians(lastAngle - i * anglesDis), ballsSpeed);
            this.velocities.add(v);
        }
    }

    /**
     * method gets a number and choose color number.
     * @param n - number for pick a color.
     * @return a color.
     */
    private Color colorChoose(int n) {
        switch (n) {
            case 0:
            case 1:
                return Color.red;
            case 2:
            case 3:
                return Color.orange;
            case 4:
            case 5:
                return Color.yellow;
            case 9:
            case 10:
                return Color.blue;
            case 11:
            case 12:
                return Color.pink;
            case 13:
            case 14:
                return Color.CYAN;
            default:
                return Color.green;
        }
    }
}
