// ID: 316482355
package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * KeyPressStoppableAnimation - a decoration class, gets an animation and makes it stoppable according to key press.
 */
public class KeyPressStoppableAnimation implements Animation {

    // animation - animation to decorate. key - key that stop the animation.
    // keyboard -  a keyboard sensor to know key is pressed.
    // isAlreadyPressed - hold info whether key is pressed before animation started.
    private Animation animation;
    private String key;
    private KeyboardSensor keyboard;
    private boolean isAlreadyPressed;

    /**
     * constructor - gets an animation, key, and keyboard, and decorate the animation so pressing key stop it.
     * @param sensor - keyboard sensor.
     * @param key - the key to stop animation.
     * @param animation - animation that decorated.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = sensor;
        // starts as true until proven else.
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // check if there is time that key is not pressed after animation starts and update isAlreadyPressed.
        if (!this.keyboard.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        // stop only if key wasnt pressed before animation started, and now is pressed.
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            return true;
        }
        return false;
    }
}
