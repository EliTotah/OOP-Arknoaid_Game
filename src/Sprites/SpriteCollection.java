package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * the class represent the sprite Environment, the list the sprite objects in the game.
 */
public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * default constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * constructor.
     *
     * @param list sprite list
     */
    public SpriteCollection(List<Sprite> list) {
        this.spriteList = list;
    }

    /**
     * add the given sprite to the environment.
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * remove the given sprite to the environment.
     *
     * @param s sprite object
     */
    public void removeSprite(Sprite s) {
        this.getSpriteList().remove(s);
    }

    /**
     * .
     * Return the Sprite.Sprite list
     *
     * @return the Sprite.Sprite list
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<Sprite>(this.getSpriteList());
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d surface
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<Sprite>(this.getSpriteList());
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
