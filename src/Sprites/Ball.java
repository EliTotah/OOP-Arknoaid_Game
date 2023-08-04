package Sprites;

import Collision.CollisionInfo;
import GameMain.Compare;
import GameMain.GameLevel;
import GameMain.GameEnvironment;
import Shapes.Line;
import Shapes.Point;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the class represents Sprite.Sprite.Ball.
 */
public class Ball implements Sprite {
    private static final int MAX_RADIUS = 50;
    private static final int DEFAULT_VELOCITY = 4;
    private static final int DEFAULT_MOVE = 10;
    private GameEnvironment game;
    private Shapes.Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private Shapes.Point width = new Shapes.Point(0, 0);
    private Shapes.Point height = new Shapes.Point(0, 0);

    /**
     * constructor.
     *
     * @param center point
     * @param r      radius of the ball
     * @param color  color of the ball
     */
    public Ball(Shapes.Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param x     x value of the center
     * @param y     y value of the center
     * @param r     radius of the ball
     * @param color color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Shapes.Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Return the x value of the center of the ball.
     *
     * @return Return the x value of center of the ball
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * Return the y value of the center of the ball.
     *
     * @return the y value of center of the ball
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * .
     * the method returns the radius of the ball
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * .
     * the method returns the color of the ball
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * .
     * the method returns the GameMain.Game environment of the ball
     *
     * @return the GameMain.Game environment of the ball
     */

    public GameEnvironment getGame() {
        return this.game;
    }

    /**
     * .
     * the method set the GameMain.Game environment of the ball
     *
     * @param ge the GameMain.Game environment of the ball
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.game = ge;
    }

    /**
     * .
     * the method set the height of the board
     *
     * @param height point of the height of the board
     */
    public void setHeight(Shapes.Point height) {
        this.height = height;
    }

    /**
     * .
     * the method set the width of the board
     *
     * @param width point of the width of the board
     */
    public void setWidth(Shapes.Point width) {
        this.width = width;
    }

    /**
     * .
     * the method draw the ball on the given DrawSurface
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * .
     * the method set the velocity of the ball
     *
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * .
     * the method set the velocity of the ball
     *
     * @param dx dx velocity of the ball
     * @param dy dy velocity of the ball
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * .
     * the method return the velocity of the ball
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * .
     * the method create velocity by the given radius
     *
     * @param r radius of ball
     * @return Sprite.Velocity
     */
    public Velocity createVelocity(int r) {
        if (r >= MAX_RADIUS) {
            return new Velocity(DEFAULT_VELOCITY, DEFAULT_VELOCITY);
        }
        double dx = Math.abs(r - MAX_RADIUS);
        double dy = Math.abs(r - MAX_RADIUS);
        return new Velocity(dx, dy);
    }

    /**
     * the method check if the ball collide with block or paddle and move the ball one step in accordance
     * or replace the ball.
     * if the ball collide with block or paddle it will change direction.
     */
    public void moveOneStep() {
        int flag = 0;
        // point that represent where the velocity will take the ball if no collisions will occur
        Shapes.Point nextLocation = this.getVelocity().applyToPoint(this.center);
        // line that represent how the ball will move without any obstacles
        Line trajectory = new Line(this.center, nextLocation);
        // the closet collision point between the collidable object and the ball
        CollisionInfo ci = this.getGame().getClosestCollision(trajectory);
        // if there isn't collision - move the ball one step
        if (ci == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // the upper left point of the collidable object
            Shapes.Point upperLeftOfCollision = ci.collisionObject().getCollisionRectangle().getUpperLeft();
            // // the width left point of the collidable object
            double width = ci.collisionObject().getCollisionRectangle().getWidth();
            // the height left point of the collidable object
            double height = ci.collisionObject().getCollisionRectangle().getHeight();
            // set the new velocity of the ball in accordance to the collision point
            this.setVelocity(ci.collisionObject().hit(this, ci.collisionPoint(), this.getVelocity()));
            // if the collision point is at the bottom line of the object - we move the ball to "almost" the hit point
            if (Compare.doubleEquals(ci.collisionPoint().getY(), upperLeftOfCollision.getY() + height)) {
                this.center = new Shapes.Point(this.center.getX(), ci.collisionPoint().getY() + this.getSize());
                flag = 1;
                /* if the collision point is at the upper line of the object -
                   we move the ball to "almost" the hit point */
            } else if (Compare.doubleEquals(ci.collisionPoint().getY(), upperLeftOfCollision.getY())) {
                this.center = new Shapes.Point(ci.collisionPoint().getX(), ci.collisionPoint().getY() - this.getSize());
                flag = 2;
                /* if the collision point is at the left line of the object -
                   we move the ball to "almost" the hit point */
            } else if (Compare.doubleEquals(ci.collisionPoint().getX(), (upperLeftOfCollision.getX()))) {
                this.center = new Shapes.Point(ci.collisionPoint().getX() - this.getSize(), ci.collisionPoint().getY());
                flag = 3;
                /* if the collision point is at the right line of the object -
                   we move the ball to "almost" the hit point */
            } else if (Compare.doubleEquals(ci.collisionPoint().getX(), upperLeftOfCollision.getX() + width)) {
                this.center = new Shapes.Point(ci.collisionPoint().getX() + this.getSize(),
                        ci.collisionPoint().getY());
                flag = 4;
            }
            // if the ball stuck in block
            if ((this.center.getX() >= upperLeftOfCollision.getX())
                    && (this.center.getX() <= upperLeftOfCollision.getX() + width)
                    && (this.center.getY() >= upperLeftOfCollision.getY())
                    && (this.center.getY() <= upperLeftOfCollision.getY() + height)) {
                switch (flag) {
                    case 1:
                        this.center = new Shapes.Point(this.center.getX(),
                                upperLeftOfCollision.getY() + height + this.getSize() + DEFAULT_MOVE);
                        break;
                    case 2:
                        this.center = new Shapes.Point(this.center.getX(),
                                upperLeftOfCollision.getY() - this.getSize() - DEFAULT_MOVE);
                        break;
                    case 3:
                        this.center = new Shapes.Point(upperLeftOfCollision.getX() + width
                                + this.getSize() + DEFAULT_MOVE, ci.collisionPoint().getY());
                        break;
                    case 4:
                        this.center = new Point(upperLeftOfCollision.getX() - this.getSize() - DEFAULT_MOVE,
                                ci.collisionPoint().getY());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * .
     * we move the ball one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
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
        g.addSprite(this);
    }

    /**
     * .
     * This method will be in charge of removing the ball and the block to the game,
     * calling the appropriate game methods.
     *
     * @param g GameMain.Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * the method check if the ball stay in the limits of the board and move the ball one step in accordance.
     * if the ball cross the board it will change direction.
     */
    public void moveWithoutCollision() {
        // move the ball one step by the values of the velocity
        this.center = this.getVelocity().applyToPoint(this.center);
        /* if the diameter of the ball is equal to the limits of the rectangle,
           put the ball in the center of the rectangle and don't move him
           (he is all over the rectangle, he doesn't have space to move). */
        if (this.getSize() == (this.height.getX() - this.width.getX()) / 2) {
            this.center = new Shapes.Point(this.width.getX() + this.getSize(), this.width.getY() + this.getSize());
         /* if the ball cross the boards limits from the top left side so
           change his direction and his stick his position to the limit */
        } else if (((this.center.getX() - this.getSize()) <= this.width.getX())
                && ((this.center.getY() - this.getSize()) <= this.width.getY())) {
            this.center = new Shapes.Point(this.width.getX() + this.getSize(), this.width.getY() + this.getSize());
            this.velocity = new Velocity((this.velocity.getDX()), (this.velocity.getDY()));
        /* if the ball cross the boards limits from the left side so
           change his direction and his stick his position to the limit */
        } else if (((this.center.getX() - this.getSize()) <= this.width.getX())) {
            this.center = new Shapes.Point(this.width.getX() + this.getSize(), this.center.getY());
            this.velocity = new Velocity(-(this.velocity.getDX()), (this.velocity.getDY()));
        /* if the ball cross the boards limits from the right side so
           change his direction and his stick his position to the limit */
        } else if ((this.center.getX() + this.getSize()) >= this.height.getX()) {
            this.center = new Shapes.Point((this.height.getX() - this.getSize()), this.center.getY());
            this.velocity = new Velocity(-(this.velocity.getDX()), (this.velocity.getDY()));
        /* if the ball cross the boards limits from the upper side so
           change his direction and his stick his position to the limit */
        } else if ((this.center.getY() - getSize()) <= this.width.getY()) {
            this.center = new Shapes.Point(this.center.getX(), this.width.getX() + this.getSize());
            this.velocity = new Velocity((this.velocity.getDX()), -(this.velocity.getDY()));
         /* if the ball cross the boards limits from the lower side so
           change his direction and his stick his position to the limit */
        } else if ((this.center.getY() + this.getSize()) >= this.height.getY()) {
            this.center = new Shapes.Point(this.center.getX(), this.height.getY() - this.getSize());
            this.velocity = new Velocity((this.velocity.getDX()), -(this.velocity.getDY()));
        }
    }
}
