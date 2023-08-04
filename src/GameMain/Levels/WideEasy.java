package GameMain.Levels;

import Collision.Block;
import GameMain.GameLevel;
import GameMain.LevelInformation;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class represent the second Level.
 */
public class WideEasy implements LevelInformation {
    private static final int X_POSITION_BALL = 390;
    private static final int Y_POSITION_BALL = 540;

    /**
     * the method return the number Of Balls in the level.
     *
     * @return the number Of Balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * the method initial The velocity of each ball.
     *
     * @return list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int start = 95;
        for (int i = 0; i < numberOfBalls(); i++) {
            list.add(new Velocity().fromAngleAndSpeed(start, 6));
            start -= 20;
        }
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
        return 600;
    }

    /**
     * the method return the level name.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
                d.setColor(Color.LIGHT_GRAY);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                int s = 20;
                for (int i = 0; i < 70; i++) {
                    d.setColor(new Color(240, 198, 73));
                    d.drawLine(150, 150, s, 300);
                    s += 10;
                }
                d.setColor(new Color(240, 182, 73));
                d.fillCircle(150, 150, 80);
                d.setColor(new Color(240, 198, 73));
                d.fillCircle(150, 150, 65);
                d.setColor(Color.YELLOW);
                d.fillCircle(150, 150, 50);

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
        List<Block> list = new ArrayList<>();
        // count of the blocks
        int countBlocks = 15;
        // the width and height of the blocks
        double width = 50, height = 30;
        // the position of the blocks
        double xPosition = 25;
        for (int i = 0; i < countBlocks; i++) {
            // create the block
            Block block = new Block(new Shapes.Rectangle(new Shapes.Point(xPosition, 300),
                    width, height), Color.black);
            if (i == 0 || i == 1) {
                block.setColor(Color.RED);
            }
            if (i == 2 || i == 3) {
                block.setColor(Color.orange);
            }
            if (i == 4 || i == 5) {
                block.setColor(Color.YELLOW);
            }
            if (i == 6 || i == 7 || i == 8) {
                block.setColor(Color.GREEN);
            }
            if (i == 10 || i == 9) {
                block.setColor(Color.blue);
            }
            if (i == 12 || i == 11) {
                block.setColor(Color.PINK);
            }
            if (i == 14 || i == 13) {
                block.setColor(Color.cyan);
            }
            list.add(block);
            // change the xPosition of the next block
            xPosition += width;
        }
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
        return blocks().size();
    }

    /**
     * the method create list of The starting point of the balls.
     *
     * @return list of points
     */
    @Override
    public List<Shapes.Point> startingPointsOfBalls() {
        List<Shapes.Point> pointsList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            pointsList.add(new Shapes.Point(X_POSITION_BALL, Y_POSITION_BALL));
        }
        return pointsList;
    }
}
