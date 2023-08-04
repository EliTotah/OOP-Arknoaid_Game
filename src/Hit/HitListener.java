package Hit;

import Collision.Block;
import Sprites.Ball;

/**
 * the class HitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that being Hit
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
