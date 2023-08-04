package GameMain.Levels;

import Collision.Block;
import GameMain.GameLevel;
import GameMain.LevelInformation;
import Shapes.Point;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class represent the first Level.
 */
public class DirectHit implements LevelInformation {
    private static final int X_POSITION_BALL = 390;
    private static final int Y_POSITION_BALL = 540;

    /**
     * the method return the number Of Balls in the level.
     *
     * @return the number Of Balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * the method initial The velocity of each ball.
     *
     * @return list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity().fromAngleAndSpeed(360, 5));
        return list;
    }

    /**
     * the method return the speed paddle.
     *
     * @return the speed paddle.
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * the method return the speed width.
     *
     * @return the speed width.
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * the method return the level name.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * the method Returns a sprite with the background of the level.
     *
     * @return sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.BLUE);
                int r = 60;
                for (int i = 0; i < 3; i++) {
                    d.drawCircle(d.getWidth() / 2 - 10, 160, r);
                    r += 30;
                }
                d.drawLine(d.getWidth() / 2 + 10, 160, d.getWidth() / 2 + 130, 160);
                d.drawLine(d.getWidth() / 2 - 10, 140, d.getWidth() / 2 - 10, 20);
                d.drawLine(d.getWidth() / 2 - 30, 160, d.getWidth() / 2 - 150, 160);
                d.drawLine(d.getWidth() / 2 - 10, 180, d.getWidth() / 2 - 10, 300);
            }

            /**
             * notify the sprite that time has passed.
             */
            @Override
            public void timePassed() {

            }

            /**
             * .
             * This method will be in charge of adding the ball and the block to the game,
             * calling the appropriate game methods.
             *
             * @param g GameMain.Game
             */
            @Override
            public void addToGame(GameLevel g) {

            }
        };
    }

    /**
     * the method create list of The Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return list of blocks
     */
    @Override
    public List<Block> blocks() {
        Shapes.Rectangle rec = new Shapes.Rectangle(new Point(376, 145), 30, 30);
        Block block = new Block(rec, Color.red);
        List<Block> list = new ArrayList<>();
        list.add(block);
        return list;
    }


    /**
     * the method Returns the Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return number of balls
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    /**
     * the method create list of The starting point of the balls.
     *
     * @return list of points
     */
    @Override
    public List<Point> startingPointsOfBalls() {
        List<Point> pointsList = new ArrayList<>();
        pointsList.add(new Point(X_POSITION_BALL, Y_POSITION_BALL));
        return pointsList;
    }
}
