package GameMain;

import Collision.Block;
import Sprites.Sprite;
import Sprites.Velocity;

import java.util.List;

/**
 * the class represent Level Information.
 */
public interface LevelInformation {

    /**
     * the method return the number Of Balls in the level.
     *
     * @return the number Of Balls in the level.
     */
    int numberOfBalls();

    /**
     * the method initial The velocity of each ball.
     *
     * @return list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * the method return the speed paddle.
     *
     * @return the speed paddle.
     */
    int paddleSpeed();

    /**
     * the method return the speed width.
     *
     * @return the speed width.
     */
    int paddleWidth();

    /**
     * the method return the level name.
     *
     * @return the level name
     */
    String levelName();

    /**
     * the method Returns a sprite with the background of the level.
     *
     * @return sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * the method create list of The Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * the method Returns the Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of balls
     */
    int numberOfBlocksToRemove();

    /**
     * the method create list of The starting point of the balls.
     *
     * @return list of points
     */
    List<Shapes.Point> startingPointsOfBalls();
}
