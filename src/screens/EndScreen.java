// ID: 316482355
package screens;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * EndScreen - animation of end screen - can be winner screen or game over screen.
 */
public class EndScreen implements Animation {

    // winner - true if game ended because won, and false if lost. score - total score when game is over.
    private boolean winner;
    private int score;

    /**
     * constructor.
     * @param isWinner - true if game ended because won, and false if lost.
     * @param score - total score when game is over.
     */
    public EndScreen(boolean isWinner, int score) {
        this.winner = isWinner;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String stringScore = Integer.toString(this.score);
        // text when user won the  game.
        if (winner) {
            d.setColor(Color.BLUE);
            d.drawText(30, d.getHeight() / 2, "You Win! Your Score is " + stringScore, 32);
        } else {
            // text when user lost.
            d.setColor(Color.red);
            d.drawText(30, d.getHeight() / 2, "Game Over. Your Score is " + stringScore, 32);
        }
    }

    @Override
    public boolean shouldStop() { return false; }
}
