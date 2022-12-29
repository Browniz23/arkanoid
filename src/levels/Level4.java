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
 * Level4 - forth level, difficulty - hard.
 */
public class Level4 implements LevelInformation {

    // WIDTH - game board width, HEIGHT - game board height.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // blocks - list of block in level. background - background of level. velocities - list of balls velocities.
    private List<Block> blocks;
    private Block background;
    private List<Velocity> velocities;

    /**
     * Level4 constructor. creates background, blocks and balls velocities.
     */
    public Level4() {
        createBackground();
        createBlocks();
        createBallsVelocities();
    }

    @Override
    public int numberOfBalls() {
        return 3;
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
     * blocks arranged in 7 rows entire width.
     */
    public void createBlocks() {
        this.blocks = new ArrayList<>();
        int rowNum = 7, blocksNum = 15, borderWidth = 10, blockWidth = (WIDTH - borderWidth) / blocksNum;
        int blockHeight = 25;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < blocksNum; j++) {
                int xCoordinate = WIDTH - borderWidth - blockWidth * (j + 1);
                int yCoordinate = HEIGHT / rowNum + blockHeight * i;
                // start point of each block. height calculated according to board height.
                Point p = new Point(xCoordinate, yCoordinate);
                Rectangle rec = new Rectangle(p, blockWidth, blockHeight);
                // choose color, so every row has same color.
                Block block = new Block(rec, colorChoose(i));
                this.blocks.add(block);
            }
        }
    }

    /**
     * method creates blue background.
     */
    public void createBackground() {
        int scoreBlockWidth = 20;
        Rectangle rec = new Rectangle(new Point(0, scoreBlockWidth), WIDTH + 1, HEIGHT + 1);
        this.background = new Block(rec, Color.blue);
    }

    /**
     * method creates 3 balls velocities and add to velocities list.
     */
    public void createBallsVelocities() {
        this.velocities = new ArrayList<>();
        double angle1 = 330, angle2 = 30, angle3 = 0;
        double ballsSpeed = 4;
        Velocity v1 = Velocity.fromAngleAndSpeed(Math.toRadians(angle1), ballsSpeed);
        Velocity v2 = Velocity.fromAngleAndSpeed(Math.toRadians(angle2), ballsSpeed);
        Velocity v3 = Velocity.fromAngleAndSpeed(Math.toRadians(angle3), ballsSpeed);
        velocities.add(v1);
        velocities.add(v2);
        velocities.add(v3);
    }

    /**
     * method gets a number and choose color using the number.
     * @param n - number for pick a color.
     * @return a color.
     */
    private Color colorChoose(int n) {
        switch (n) {
            case 0:
                return Color.gray;
            case 1:
                return Color.red;
            case 2:
                return Color.yellow;
            case 3:
                return Color.green;
            case 4:
                return Color.white;
            case 5:
                return Color.pink;
            default:
                return Color.cyan;
        }
    }
}
