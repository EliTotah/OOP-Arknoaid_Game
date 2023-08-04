package Collision;

import GameMain.GameLevel;
import Hit.HitListener;
import Hit.HitNotifier;
import Shapes.Line;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * .
 * the class represent block, implements Collision.Collidable, Sprite.Sprite interfaces.
 */

public class Block implements Collidable, Sprite, HitNotifier {

    private static final int UPPER_LINE = 0;
    private static final int BOTTOM_LINE = 2;
    private static final int RIGHT_LINE = 1;
    private static final int LEFT_LINE = 3;
    private Rectangle rect;
    private java.awt.Color color;
    private java.util.List<HitListener> hitListeners;

    /**
     * .
     * Constructor
     *
     * @param rect  the Properties of the block
     * @param color the color of the block
     */
    public Block(Shapes.Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * the method Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    public Shapes.Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * the method return the color of the block.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * the method return the list of the listneres.
     *
     * @return the list of the listneres.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * the method set the color of the block.
     *
     * @param color color of the block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * .
     * the method draw the block on the given DrawSurface
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        double width = this.getCollisionRectangle().getWidth();
        double height = this.getCollisionRectangle().getHeight();
        surface.setColor(this.getColor());
        surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
        surface.setColor(Color.black);
        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * the method Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter          the ball that hit the block
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Shapes.Point[] corners = this.rect.rectCorners();
        Line[] rectLines = this.rect.rectLines(corners);
        int horizontalFlag = 0, verticalFlag = 0;
        for (int i = 0; i < rectLines.length; i++) {
            if (rectLines[i].onTheLine(collisionPoint)) {
                // if the collision point is in horizontal lines - we change the vertical direction
                if (i == UPPER_LINE || i == BOTTOM_LINE) {
                    horizontalFlag = 1;
                    //return new Sprite.Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                    // if the collision point is in verticals lines - we change the horizontal direction
                } else if (i == RIGHT_LINE || i == LEFT_LINE) {
                    verticalFlag = 1;
                    //return new Sprite.Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
                }
            }
        }
        // Notice that we changed the hit method to include a "Ball hitter" parameter -- update the
        // Collidable interface accordingly.
        this.notifyHit(hitter);
        // if the collision point is in the edge of the block
        if (horizontalFlag == 1 && verticalFlag == 1) {
            return new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY());
            // if the collision point is in the horizontal direction of the block
        } else if (horizontalFlag == 1) {
            return new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
            // if the collision point is in the vertical direction of the block
        } else if (verticalFlag == 1) {
            return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
        }
        return currentVelocity;
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * .
     * This method will be in charge of adding the block to the game,
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


    /**
     * the method notify all the HitListeners that hit event were happened.
     *
     * @param hitter the hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.getHitListeners());
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.getHitListeners().add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.getHitListeners().remove(hl);
    }

}