package Sprites;

import Collision.Block;
import GameMain.GameLevel;
import Hit.Counter;
import Hit.ScoreTrackingListener;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator will be in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {

    private Block b;
    private ScoreTrackingListener stl;


    /**
     * Constructor.
     *
     * @param b the block that will show the score
     */
    public ScoreIndicator(Block b) {
        this.b = b;
        this.stl = new ScoreTrackingListener(new Counter(), new Counter());

    }

    /**
     * Constructor.
     *
     * @param b   the block that will show the score
     * @param stl the ScoreTrackingListener
     */
    public ScoreIndicator(Block b, ScoreTrackingListener stl) {
        this.b = b;
        this.stl = stl;
    }

    /**
     * return the ScoreTrackingListener.
     *
     * @return ScoreTrackingListener
     */
    public ScoreTrackingListener getStl() {
        return stl;
    }

    /**
     * return the block that will show the score.
     *
     * @return the block that will show the score
     */
    public Block getB() {
        return b;
    }

    /**
     * the method Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    public Shapes.Rectangle getCollisionRectangle() {
        return this.getB().getCollisionRectangle();
    }

    /**
     * the method return the color of the block.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.getB().getColor();
    }

    /**
     * .
     * the method draw the block on the given DrawSurface
     *
     * @param d DrawSurface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // draw the block
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        double width = this.getCollisionRectangle().getWidth();
        double height = this.getCollisionRectangle().getHeight();
        d.setColor(this.getColor());
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) x, (int) y, (int) width, (int) height);
        // write the score
        String s = "Score: " + this.getStl().getCurrentScore().getValue();
        d.drawText(360, 15, s, 17);
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
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
