package others;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import listeners.ScoreTrackingListener;
import screens.EndScreen;
import screens.GameLevel;
import screens.KeyPressStoppableAnimation;

import java.util.List;

/**
 * GameFlow - class that runs levels from a list, one after another.
 */
public class GameFlow {

    // keyboardSensor - keyboardSensor of gui. animationRunner - animationRunner that runs each level.
    // gui - gui board, scoreCounter - counter of score for all levels together.
    // scoreListener - listener to score update.
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter scoreCounter;
    private ScoreTrackingListener scoreListener;

    /**
     * constructor. get a gui, animation runner, keyboard sensor and starts score count amd listener.
     * @param ar - animation runner to run the levels.
     * @param ks - keyboard sensor.
     * @param gui - gui board.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.scoreCounter = new Counter(0);
        this.scoreListener = new ScoreTrackingListener(scoreCounter);
    }

    /**
     * method gets list of levels and runs all levels one after another.
     * @param levels - list of levels (levelInformation).
     */
    public void runLevels(List<LevelInformation> levels) {
        // loop runs over levels.
        for (LevelInformation levelInfo : levels) {
            // creates a Gamelevel to run current level.
            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, this.gui,
                    scoreListener, scoreCounter);
            level.initialize();
            level.run();
            // case no balls left - game over.
            if (level.isDead()) {
                EndScreen endGame = new EndScreen(false, scoreCounter.getValue());
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        this.keyboardSensor.SPACE_KEY, endGame));
                this.gui.close();
                break;
            }
        }
        // case all levels end - win.
        EndScreen endGame = new EndScreen(true, scoreCounter.getValue());
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                this.keyboardSensor.SPACE_KEY, endGame));
        this.gui.close();
    }
}