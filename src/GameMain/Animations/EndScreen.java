package GameMain.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * the class represnt the end screen, if we won the game or if we died.
 */
public class EndScreen implements Animation {
    private int score;
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean win;

    /**
     * Constructor.
     *
     * @param k       the KeyboardSensor
     * @param num     the score
     * @param victory boolean - we won or not
     */
    public EndScreen(KeyboardSensor k, int num, boolean victory) {
        this.keyboard = k;
        this.score = num;
        this.stop = false;
        this.win = victory;
    }

    /**
     * .
     * the method run one frame of the game
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // if the user won
        if (this.win) {
            d.drawText(220, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
            // if the user died
        } else {
            d.drawText(220, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
        }
        d.drawText(220, d.getHeight() - 32, "Press space to continue", 32);
    }

    /**
     * the method check if the game should stop.
     *
     * @return boolean - true if we need to stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
