package GameMain.Animations;

import biuoop.DrawSurface;

/**
 * the class represent animation object.
 */
public interface Animation {
    /**
     * .
     * the method run one frame of the game
     *
     * @param d DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * the method check if the game should stop.
     *
     * @return boolean - true if we need to stop
     */
    boolean shouldStop();
}
