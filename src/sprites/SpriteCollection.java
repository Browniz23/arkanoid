// ID: 316482355

package sprites;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * sprites.SpriteCollection - a list of interfaces.Sprite objects.
 */
public class SpriteCollection {

    // sprites - list of sprites.
    private List<Sprite> sprites;

    /**
     * constructor - creates a new Array list.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * method gets a sprite and add's it to the sprite list.
     * @param s - a sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }


    /**
     * method notify all sprites in list that time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tempSprites = new ArrayList<>(this.sprites);
        for (Sprite s : tempSprites) {
            s.timePassed();
        }
    }

    /**
     * method gets a draw surface and draw on it all sprites in list.
     * @param d - draw surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> tempSprites = new ArrayList<>(this.sprites);
        for (Sprite s : tempSprites) {
            s.drawOn(d);
        }
    }

    /**
     * the method returns list of sprites.
     * @return list of this sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}
