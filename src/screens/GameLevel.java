// ID: 316482355

package screens;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.GameEnvironment;
import collidables.Paddle;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import others.AnimationRunner;
import others.Counter;
import sprites.Ball;
import sprites.LevelName;
import sprites.ScoreIndicator;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * GameLevel - a game level animation. the user can control a paddle with left and right keys in keyboard.
 */
public class GameLevel implements Animation {

    // sprites - sprites collection in game. environment - collidables collection in game. gui - screen board of game.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    // blockCounter - counter of blocks in game. blockRemover - listen to hit and remove block when hit.
    // ballsCounter - counter of balls in game. ballRemover - listen to hit and remove ball when hit the lower border.
    // scoreCounter - counter of score in game. scoreListener - listen to block hit and update score.
    private Counter blocksCounter;
    private BlockRemover blockRemover;
    private Counter ballsCounter;
    private BallRemover ballRemover;
    private Counter scoreCounter;
    private ScoreTrackingListener scoreListener;

    // runner - AnimationRunner that runs the game in loop. keyboard - keyboard sensor.
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    // constants: WIDTH - of board. HEIGHT - of board. RADIUS - of both balls.
    // START_P1 - start point of first ball.
    // PADDLE_HEIGHT - paddle's height. SCORE_BLOCK_HEIGHT - height of score presented on board.
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int RADIUS = 5;
    private static final int PADDLE_HEIGHT = 20;
    private static final int SCORE_BLOCK_HEIGHT = 20;
    // background - block of background. levelInformation - current level information. isDead - true when no ball left.
    private Block background;
    private LevelInformation levelInformation;
    private boolean isDead;

    /**
     * Constructor. gets information for specific level, an animation runner, keyboard sensor, gui, score listener and
     * score counter. creates a GameLevel using those parameters.
     * @param levelInfo - level information.
     * @param ar - animation runner to run level game animation.
     * @param ks - keyboard sensor for gui board.
     * @param gui - gui board to show game.
     * @param scoreListener - track score when need to be updated.
     * @param scoreCounter - counter for score.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor ks, GUI gui,
                     ScoreTrackingListener scoreListener, Counter scoreCounter) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.blocksCounter = new Counter(levelInfo.numberOfBlocksToRemove());
        this.blockRemover = new BlockRemover(this, blocksCounter);
        this.ballsCounter = new Counter(levelInfo.numberOfBalls());
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.scoreCounter = scoreCounter;
        this.scoreListener = scoreListener;
        this.runner = ar;
        this.keyboard = ks;

        this.levelInformation = levelInfo;
        this.background = (Block) levelInfo.getBackground();
        this.isDead = false;
    }

    /**
     * method adds a collidable object to game.
     * @param c - collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * method adds a sprite to the game.
     * @param s - a sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * method initialize game. add a background, 2 balls, a paddle, borders of game, and blocks.
     */
    public void initialize() {
        this.addBackground();
        this.addBalls();
        this.addPaddle();
        this.addBorders();
        this.addBlocks();
        this.addTopAndScore();
        this.addLevelName();
    }

    /**
     * method run the game in infinite loop.
     */
    public void run() {
        // numOfSecond - seconds to show each num in countdown. countFrom - num to start countdown from.
        int numOfSecond = 1, countFrom = 3;
        // run countdown animation.
        this.runner.run(new CountdownAnimation(numOfSecond, countFrom, this.sprites));
        // run current animation.
        this.runner.run(this);
    }

    /**
     * method add's black background as a block size whole screen.
     */
    private void addBackground() {
        this.background.addToGame(this);
    }

    /**
     * method create ball according to given velocities and add to game.
     */
    private void addBalls() {
        // start y point is above paddle so minus 10.
        Point startPoint = new Point(WIDTH / 2, HEIGHT - PADDLE_HEIGHT - 10);
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(startPoint, RADIUS, Color.white, this.environment);
            ball.setVelocity(v);
            ball.addToGame(this);
        }
    }

    /**
     * method add's a paddle to game.
     */
    private void addPaddle() {
        // x coordinate calculated so paddle will be in the middle.
        Point startP = new Point((WIDTH - this.levelInformation.paddleWidth()) / 2, HEIGHT - PADDLE_HEIGHT);
        Rectangle paddleRec = new Rectangle(startP, this.levelInformation.paddleWidth(), PADDLE_HEIGHT);
        Paddle paddle = new Paddle(paddleRec, Color.ORANGE, this.gui, 0, WIDTH,
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * method add's 4 blocks as borders of game.
     */
    private void addBorders() {
        int borderWidth = 10;
        Rectangle rec1 = new Rectangle(new Point(0, SCORE_BLOCK_HEIGHT), WIDTH, borderWidth);
        Block block1 = new Block(rec1, Color.GRAY);
        block1.addToGame(this);
        // block2 is death border.
        Rectangle rec2 = new Rectangle(new Point(0, HEIGHT), WIDTH, borderWidth);
        Block block2 = new Block(rec2, Color.GRAY);
        block2.addToGame(this);
        block2.addHitListener(this.ballRemover);
        Rectangle rec3 = new Rectangle(new Point(0, SCORE_BLOCK_HEIGHT), borderWidth, HEIGHT);
        Block block3 = new Block(rec3, Color.GRAY);
        block3.addToGame(this);
        Rectangle rec4 = new Rectangle(new Point(WIDTH - 10, SCORE_BLOCK_HEIGHT), borderWidth, HEIGHT);
        Block block4 = new Block(rec4, Color.GRAY);
        block4.addToGame(this);
    }


    /**
     * method add's to game 6 rows of blocks, 12 blocks in the top one, when each row has 1 less then the row in it top.
     */
    private void addBlocks() {
        for (Block b : this.levelInformation.blocks()) {
            // add bRemover to use as interfaces.HitListener to block. and add block to game.
            b.addHitListener(this.blockRemover);
            b.addHitListener(this.scoreListener);
            b.addToGame(this);
        }
    }

    /**
     * the method gets a collidable and removes it from this game.
     * @param c - a collidable to remove.
     */
    public void removeCollidable(Collidable c) {
         if (c != null) {
             this.environment.getCollideables().remove(c);
         }
    }

    /**
     * the method gets a sprite and removes it from this game.
     * @param s - a sprite to remove.
     */
    public void removeSprite(Sprite s) {
        if (s != null) {
            this.sprites.getSprites().remove(s);
        }
    }

    /**
     * method creates and add to game a ScoreIndicator that present the score according to score count.
     * in addition creates and add to game high block to present the score on it.
     */
    private void addTopAndScore() {
        Rectangle rec = new Rectangle(new Point(0, 0), WIDTH, SCORE_BLOCK_HEIGHT);
        Block block = new Block(rec, Color.WHITE);
        block.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(scoreIndicator);
    }

    /**
     * method returns this.blockRemover.
     * @return this.blockRemover.
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * method returns this.ballRemover.
     * @return this.ballRemover.
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * method returns this.scoreListener.
     * @return this.scoreListener.
     */
    public ScoreTrackingListener getScoreListener() {
        return this.scoreListener;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw all sprites in game, show the screen and update all sprites to act as they should when time passed.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if user clicked 'p' - pause the game by using pause screen animation.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        // case no blocks left, gain 100 points and exit the game.
        if (this.blocksCounter.getValue() <= 0) {
            this.scoreCounter.increase(100);
            return true;
        }
        // case no balls left, exit the game.
        if (this.ballsCounter.getValue() <= 0) {
            this.isDead = true;
            return true;
        }
        return false;
    }

    /**
     * method returns whether user lost the game.
     * @return true if no balls left, else false.
     */
    public boolean isDead() {
        return this.isDead;
    }

    /**
     * method adds level name on top of the screen.
     */
    public void addLevelName() {
        this.sprites.addSprite(new LevelName(this.levelInformation.levelName()));
    }
}
