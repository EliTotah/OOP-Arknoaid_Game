package Collision;

import GameMain.GameLevel;
import Shapes.Line;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * the class represent paddle.
 */
public class Paddle implements Sprite, Collidable {

    private static final int LEFT_BORDER = 1;
    private static final int RIGHT_BORDER = 2;
    private static final int BORDER_WIDTH = 25;
    private static final int BORDER_HEIGHT = 25;
    private static final int BOTTOM_LINE = 2;
    private static final int RIGHT_LINE = 1;
    private static final int LEFT_LINE = 3;
    private static final int MIDDLE_REGION = 2;
    private static final int DEGREES_IN_CIRCLE = 360;
    private static final int START_ANGLE = 300;
    private static final int EXTRA_ANGLE = 30;
    private static final int SUM_REGIONS = 5;
    private GameLevel gameLevel;
    private int speed;
    private biuoop.KeyboardSensor keyboard;
    private Shapes.Rectangle rect;
    private java.awt.Color color;

    /**
     * .
     * constructor
     *
     * @param keyboard KeyboardSensor
     * @param rect     the shape of the paddle
     * @param c        the color
     * @param speed    the speed of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Shapes.Rectangle rect, Color c, int speed) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = c;
        this.speed = speed;
    }

    /**
     * the method set the game environment.
     *
     * @param gameLevel the game environment.
     */
    public void setGame(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * the method return the game environment of the paddle.
     *
     * @return the game environment of the paddle.
     */
    public GameLevel getGame() {
        return gameLevel;
    }

    /**
     * the method return the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * the method move the paddle left.
     */
    public void moveLeft() {
        DrawSurface d = this.getGame().getGui().getDrawSurface();
        // check if there is no collision with the left border, so we are in the range of the screen
        if (this.checkLimits(d) == 0 || this.checkLimits(d) == RIGHT_BORDER) {
            // move the paddle left
            Point newPoint =
                    new Shapes.Point(this.getCollisionRectangle().getUpperLeft().getX() - this.getSpeed(),
                            this.getCollisionRectangle().getUpperLeft().getY());
            Shapes.Rectangle newRec = new Shapes.Rectangle(newPoint, getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
            this.setRect(newRec);
            // collision with the left limit
        } else if (this.checkLimits(d) == LEFT_BORDER) {
            // stick the paddle to the left border
            double y = d.getHeight() - this.rect.getHeight() - BORDER_HEIGHT;
            double width = this.getCollisionRectangle().getWidth();
            double height = this.getCollisionRectangle().getHeight();
            Shapes.Rectangle r = new Shapes.Rectangle(new Shapes.Point(BORDER_WIDTH, y), width, height);
            this.setRect(r);
        }
    }

    /**
     * the method move the paddle right.
     */
    public void moveRight() {
        DrawSurface d = this.getGame().getGui().getDrawSurface();
        // check if there is no collision with the right border, so we are in the range of the screen
        if (this.checkLimits(d) == 0 || this.checkLimits(d) == LEFT_BORDER) {
            // move the paddle right
            Shapes.Point newPoint =
                    new Shapes.Point(this.getCollisionRectangle().getUpperLeft().getX() + this.getSpeed(),
                            this.getCollisionRectangle().getUpperLeft().getY());
            Rectangle newRec = new Shapes.Rectangle(newPoint, getCollisionRectangle().getWidth(),
                    this.getCollisionRectangle().getHeight());
            this.setRect(newRec);
            // stick the paddle to the right border
        } else if (this.checkLimits(d) == RIGHT_BORDER) {
            double x = d.getWidth() - this.rect.getWidth() - BORDER_WIDTH;
            double y = d.getHeight() - this.rect.getHeight() - BORDER_HEIGHT;
            double width = this.getCollisionRectangle().getWidth();
            double height = this.getCollisionRectangle().getHeight();
            Shapes.Rectangle r = new Shapes.Rectangle(new Shapes.Point(x, y), width, height);
            this.setRect(r);
        }
    }

    /**
     * .
     * the method check if the paddle stay in the range of the screen
     *
     * @param d surface
     * @return true if the paddle is in the range of the screen and false if not
     */
    public int checkLimits(DrawSurface d) {
        // if the paddle going to stuck in the left border
        if ((this.rect.getUpperLeft().getX() - this.getSpeed()) <= BORDER_WIDTH) {
            return LEFT_BORDER;
            // if the paddle going to stuck in the right border
        } else if ((this.rect.getUpperLeft().getX() + this.rect.getWidth() + this.getSpeed())
                >= (d.getWidth() - BORDER_WIDTH)) {
            return RIGHT_BORDER;
        }
        return 0;
    }

    /**
     * the method return the paddle color.
     *
     * @return the paddle color
     */
    public Color getColor() {
        return color;
    }

    /**
     * the method set the paddle shape.
     *
     * @param rect the paddle shape
     */
    public void setRect(Shapes.Rectangle rect) {
        this.rect = rect;
    }

    /**
     * the method set the paddle color.
     *
     * @param color the paddle color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * the method set the paddle keyboard.
     *
     * @param keyboard the paddle keyboard
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * the method return the paddle shape.
     *
     * @return the paddle shape
     */
    public Shapes.Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * the method move the paddle left or right in accordance to the key that pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * .
     * the method draw the block on the given DrawSurface
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        double width = this.getCollisionRectangle().getWidth();
        double height = this.getCollisionRectangle().getHeight();
        d.setColor(this.getColor());
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * the method Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the collidable
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    public Velocity hit(Ball hitter, Shapes.Point collisionPoint, Velocity currentVelocity) {
        Shapes.Point[] corners = this.rect.rectCorners();
        Line[] rectLines = this.rect.rectLines(corners);
        Line[] regions = getRegions();
        double speed = Math.sqrt((currentVelocity.getDX() * currentVelocity.getDX())
                + (currentVelocity.getDY() * currentVelocity.getDY()));
        // check if the ball collide with bottom, the left or the right side of the paddle
        for (int i = 0; i < rectLines.length; i++) {
            if (rectLines[i].onTheLine(collisionPoint)) {
                // horizontal lines
                if (i == BOTTOM_LINE) {
                    return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                    // verticals lines
                } else if (i == RIGHT_LINE || i == LEFT_LINE) {
                    return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
                }
            }
        }
        // if the ball collide with the upper line of the paddle, we check the region of the collision
        for (int i = 0; i < regions.length; i++) {
            if (regions[i].onTheLine(collisionPoint)) {
                if (i == MIDDLE_REGION) {
                    return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                } else {
                    // the calculation is (300 + (i*30))%360.
                    return currentVelocity.fromAngleAndSpeed(
                            ((START_ANGLE + (i * EXTRA_ANGLE)) % DEGREES_IN_CIRCLE), speed);
                }
            }
        }
        return currentVelocity;
    }

    /**
     * .
     * the method set the regions of the paddle
     *
     * @return array of lines that which is the paddle regions
     */
    public Line[] getRegions() {
        Line[] regions = new Line[5];
        double length = this.getCollisionRectangle().getWidth() / SUM_REGIONS;
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        for (int i = 0; i < regions.length; i++) {
            regions[i] = new Line(x, y, x + ((i + 1) * length), y);
        }
        return regions;
    }

    /**
     * .
     * This method will be in charge of adding the ball and the block to the game,
     * calling the appropriate game methods.
     *
     * @param g GameMain.Game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * This method will be in charge of removing the block to the game,
     * calling the appropriate game methods.
     *
     * @param g GameMain.Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}
