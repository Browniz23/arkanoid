// ID: 316482355
package interfaces;

import biuoop.DrawSurface;

/**
 * Animation - Animation that can move one frame, and has a condition for stopping.
 */
public interface Animation {
    /**
     * method forward animation in one frame. (one step).
     * @param d - gets a draw surface to draw on newer animation.
     */
    void doOneFrame(DrawSurface d);

    /**
     * method define stopping condition for animation.
     * @return true for stopping animation. else false.
     */
    boolean shouldStop();
}