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
 * the class represent the third Level.
 */
public class Green3 implements LevelInformation {

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
        return 2;
    }

    /**
     * the method initial The velocity of each ball.
     *
     * @return list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int s = 50;
        for (int i = 0; i < this.numberOfBalls(); i++) {
            list.add(new Velocity().fromAngleAndSpeed(s, 5));
            s -= 100;
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
        return 80;
    }

    /**
     * the method return the level name.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Green 3";
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
                d.setColor(new Color(2, 133, 0));
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
                d.setColor(Color.DARK_GRAY);
                d.fillRectangle(70, 400, 130, 600);
                d.fillRectangle(120, 350, 35, 50);
                d.fillRectangle(130, 200, 15, 150);

                int s = 80;
                int y = 410;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 6; j++) {
                        d.setColor(Color.WHITE);
                        d.fillRectangle(s, y, 10, 30);
                        s += 20;
                    }
                    s = 80;
                    y += 50;
                }

                d.setColor(Color.ORANGE);
                d.fillCircle(137, 200, 13);
                d.setColor(Color.RED);
                d.fillCircle(137, 200, 9);
                d.setColor(Color.WHITE);
                d.fillCircle(137, 200, 4);
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
        int countBlocks = 10;
        // the width and height of the blocks
        double width = 50, height = 30;
        // the position of the blocks
        double xPosition = SCREEN_WIDTH - width - BORDER_WIDTH, yPosition = 150;
        // list of colors
        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.blue);
        colors.add(Color.white);
        for (int i = 0; i < 5; i++) {
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
            // down the blocks one line down
            yPosition = yPosition + height;
            // Subtract one block from each row
            countBlocks--;
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
