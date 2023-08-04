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
 * the class represent the fourth Level.
 */
public class FinalFour implements LevelInformation {

    private static final int BORDER_WIDTH = 25;
    private static final int SCREEN_WIDTH = 800;
    private static final int X_POSITION_BALL = 390;
    private static final int Y_POSITION_BALL = 540;

    /**
     * the method return the number Of Balls in the level.
     *
     * @return the number Of Balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * the method initial The velocity of each ball.
     *
     * @return list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int s = 30;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            list.add(new Velocity().fromAngleAndSpeed(s, 4));
            s -= 30;
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
        return 140;
    }

    /**
     * the method return the level name.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Final Four";
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
                d.setColor(new Color(0, 130, 255));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                int x = 88;
                int y = 450;
                for (int i = 0; i < 10; i++) {
                    d.setColor(Color.WHITE);
                    d.drawLine(x, y, x - 20, 600);
                    x += 8;
                }

                d.drawLine(80, y, 80 - 20, 600);
                d.drawLine(88, y, 88 - 20, 600);
                d.drawLine(96, y, 96 - 20, 600);
                d.setColor(new Color(234, 219, 219));
                d.fillCircle(80, 440, 20);
                d.fillCircle(90, 430, 20);
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(100, 450, 20);
                d.fillCircle(120, 440, 20);
                d.setColor(new Color(211, 207, 198));
                d.fillCircle(140, 460, 25);


                x = 688;
                y = 450;
                for (int i = 0; i < 8; i++) {
                    d.setColor(Color.WHITE);
                    d.drawLine(x, y, x - 20, 600);
                    x += 7;
                }
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(688, 450, 20);
                d.fillCircle(700, 440, 20);
                d.setColor(new Color(211, 207, 198));
                d.fillCircle(720, 460, 25);
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
        double xPosition = SCREEN_WIDTH - width - BORDER_WIDTH, yPosition = 80;
        // list of colors
        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.white);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        for (int i = 0; i < 7; i++) {
            // random color for every row
            Color color = colors.get(i);
            for (int j = 0; j < countBlocks; j++) {
                // add the top line twice so the user need to hit the block 2 times to break it
                if (i == 0) {
                    // create the block
                    Block block = new Block(new Shapes.Rectangle(new Shapes.Point(xPosition, yPosition),
                            width, height), color);
                    list.add(block);
                }
                // create the block
                Block block = new Block(new Shapes.Rectangle(new Shapes.Point(xPosition, yPosition),
                        width, height), color);
                // change the xPosition of the next block
                xPosition = xPosition - width;
                list.add(block);
            }
            xPosition = SCREEN_WIDTH - width - BORDER_WIDTH;
            yPosition += height;
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
    public List<Point> startingPointsOfBalls() {
        List<Shapes.Point> pointsList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            pointsList.add(new Shapes.Point(X_POSITION_BALL, Y_POSITION_BALL));
        }
        return pointsList;
    }
}
