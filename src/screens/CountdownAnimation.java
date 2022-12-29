// ID: 316482355
package screens;

import biuoop.DrawSurface;
import interfaces.Animation;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * CountdownAnimation - animation of countdown.
 */
public class CountdownAnimation implements Animation {

    // numOfSeconds - seconds num for showing each number on board. currentCount - current num in count.
    // gameScreen - all sprites in game when game begins. startTime - start time of count for calculate time.
    private double numOfSeconds;
    private int currentCount;
    private SpriteCollection gameScreen;
    private long startTime;

    /**
     * constructor. get numOfSecond to show each count num, starting num to count from, and game screen sprites.
     * @param numOfSeconds - amount of second to show each count num.
     * @param countFrom - starting num to count from.
     * @param gameScreen - sprites collection of game to show while counting.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.currentCount = countFrom;
        this.gameScreen = gameScreen;
        // start time to show each counting num for numOfSeconds seconds.
        this.startTime = System.currentTimeMillis(); // timing
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw count text in chosen point and size.
        int xCoordinate = 400, yCoordinate = 450, size = 50;
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.cyan);
        d.drawText(xCoordinate, yCoordinate, Integer.toString(this.currentCount), size);
    }

    @Override
    public boolean shouldStop() {
        // if count is 0 or less - countdown over.
        if (this.currentCount <= 0) {
            return true;
        }
        // usedTime - how much time passed from beginning of count.
        long usedTime = System.currentTimeMillis() - this.startTime;
        int millisToSec = 1000;
        if (usedTime > this.numOfSeconds * millisToSec) {
            // update counting.
            this.currentCount--;
            this.startTime = System.currentTimeMillis(); // timing
        }
        return false;
    }
}
