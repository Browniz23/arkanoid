// ID: 316482355

import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import others.AnimationRunner;
import others.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game creates a game, initialize it, and runs it.
 */
public class Ass6Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FRAMES_PER_SECOND = 60;

    /**
     * main method create a game and runs it.
     * @param args - no need.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        Level1 l1 = new Level1();
        Level2 l2 = new Level2();
        Level3 l3 = new Level3();
        Level4 l4 = new Level4();
        for (String level : args) {
            if (level.equals("1")) {
                levels.add(l1);
            } else if (level.equals("2")) {
                levels.add(l2);
            } else if (level.equals("3")) {
                levels.add(l3);
            } else if (level.equals("4")) {
                levels.add(l4);
            }
        }
        if (levels.isEmpty()) {
            levels.add(l1);
            levels.add(l2);
            levels.add(l3);
            levels.add(l4);
        }
        GUI gui = new GUI("game", WIDTH, HEIGHT);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui, FRAMES_PER_SECOND);
        GameFlow g = new GameFlow(ar, ks, gui);
        g.runLevels(levels);
    }
}
