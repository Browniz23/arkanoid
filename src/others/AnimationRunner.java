// ID: 316482355
package others;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * AnimationRunner - gets a gui and can run Animation until its stopping condition.
 */
public class AnimationRunner {

    // gui - gui board. framesPerSecond - how much frames we want to move every second. sleeper - can sleep program.
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor. gets a gui and frames per second, and creates animationRunner.
     * @param g - gui board.
     * @param framesPerSecond - frames to move for second.
     */
    public AnimationRunner(GUI g, int framesPerSecond) {
        this.gui = g;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * method gets am animation and runs it till animation should stop.
     * @param animation - animation to run.
     */
    public void run(Animation animation) {
        // framePerSecond - frames showed per second. millisecondsPerFrame - time each frame showed in milliseconds.
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        // loop that runs the game until player lose or win.
        while (!animation.shouldStop()) {
            // startTime - current time in milliseconds. d - draw surface to draw on.
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);
            // usedTime - time passed until now from start of this round in loop.
            // milliSecondLeftToSleep - time left to delay screen.
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
