package GameMain.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * the class represnt the end screen, if we won the game or if we died.
 */
public class PauseScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param k KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * .
     * the method run one frame of the game
     *
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(170, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * the method check if the game should stop.
     *
     * @return boolean - true if we need to stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
