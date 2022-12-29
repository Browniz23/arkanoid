// ID: 316482355

package interfaces;

import biuoop.DrawSurface;

/**
 * Sprite - an object that can be drawn to the screen, and to to be notified that time passed.
 */
public interface Sprite {

    /**
     * draw this sprite to screen.
     * @param d - draw surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify this sprite that time has passed.
     */
    void timePassed();
}