package Collision;

import GameMain.GameLevel;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.Ball;
import Sprites.Velocity;
import biuoop.DrawSurface;


/**
 * the class represent collidable object.
 */
public interface Collidable {

    /**
     * the method Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * .
     * the method Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the collidable
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * .
     * the method draw the block on the given DrawSurface
     *
     * @param surface DrawSurface
     */
    void drawOn(DrawSurface surface);

    /**
     * .
     * This method will be in charge of adding the ball and the block to the game,
     * calling the appropriate game methods.
     *
     * @param g GameMain.Game
     */
    void addToGame(GameLevel g);

}
