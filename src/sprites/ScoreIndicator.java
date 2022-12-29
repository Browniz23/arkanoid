//ID: 316482355

package sprites;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import others.Counter;

import java.awt.Color;

/**
 * ScoreIndicator - a Sprite that contains a Counter for score, and can draw it on drawing surface.
 */
public class ScoreIndicator implements Sprite {

    // scoreCounter - score counter.
    private Counter scoreCounter;
    // COLOR - color of drawn score. POINT - point on board for draw the score. SIZE - size of drawing score.
    private static final Color COLOR = Color.BLACK;
    private static final Point POINT = new Point(300, 15);
    private static final int SIZE = 15;

    /**
     * Constructor. gets a others.Counter and creates a scoreIndicator according to it.
     * @param scoreCounter1 - others.Counter for score.
     */
    public ScoreIndicator(Counter scoreCounter1) {
        this.scoreCounter = scoreCounter1;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(COLOR);
        d.drawText((int) POINT.getX(), (int) POINT.getY(),
                "Score: " + Integer.toString(this.scoreCounter.getValue()), SIZE);
    }

    @Override
    public void timePassed() {

    }
}
