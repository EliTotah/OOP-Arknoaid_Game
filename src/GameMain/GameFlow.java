package GameMain;

import GameMain.Animations.AnimationRunner;
import GameMain.Animations.EndScreen;
import GameMain.Animations.KeyPressStoppableAnimation;
import Hit.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * the class represent the game flow of the game.
 */
public class GameFlow {

    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private Counter lives;

    /**
     * Constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Game", 800, 600);
        this.animationRunner = new AnimationRunner(gui);
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.score = new Counter();
        this.lives = new Counter(7);
    }

    /**
     * the method run the levels by the given order.
     *
     * @param levels list of the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (int i = 0; i < levels.size(); i++) {
            LevelInformation levelInfo = levels.get(i);
            // create game level with the level info
            GameLevel level = new GameLevel(levelInfo, this.gui, this.keyboardSensor, this.animationRunner,
                    this.score, this.lives);
            // initialize the game level
            level.initialize();
            // run the game
            level.run();
            // if the user disqualified
            this.score = level.getScore();
            if (level.getLives().getValue() == 0) {
                // print game over message
                animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndScreen(keyboardSensor, this.score.getValue(), false)));
                break;
            }
            // if the user win the game
            if ((level.getCounterBlocks().getValue() == 0) && (i == levels.size() - 1)) {
                // print you win message
                animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new EndScreen(keyboardSensor, this.score.getValue(), true)));
                break;
            }
        }
        gui.close();
    }
}
