package Sprites;

import GameMain.GameLevel;
import biuoop.DrawSurface;

/**
 * the interface represent sprite objects.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * .
     * This method will be in charge of adding the ball and the block to the game,
     * calling the appropriate game methods.
     *
     * @param g GameMain.Game
     */
    void addToGame(GameLevel g);
}
