// ID: 316482355
package screens;

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * PauseScreen - a "static" Animation of pausing screen.
 */
public class PauseScreen implements Animation {

    /**
     * constructor (empty).
     */
    public PauseScreen() {

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int x = 10, y = d.getHeight() / 2, size = 32;
        d.drawText(x, y, "paused -- press space to continue", size);
    }

    @Override
    public boolean shouldStop() { return false; }
}