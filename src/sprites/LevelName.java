// ID: 316482355

package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * LevelName - a Sprite of text - level name.
 */
public class LevelName implements Sprite {

    // the name of the level.
    private String levelName;

    /**
     * constructor. gets a string name and makes it a Sprite.
     * @param name - level name.
     */
    public LevelName(String name) {
        this.levelName = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // creates a text of levlel name.
        int xCoordinate = 600, yCoordinate = 15, size = 15;
        d.setColor(Color.BLACK);
        d.drawText(xCoordinate, yCoordinate, "Level Name: " + this.levelName, size);
    }

    @Override
    public void timePassed() {

    }
}
