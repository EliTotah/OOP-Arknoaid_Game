package Hit;

import Collision.Block;
import Sprites.Ball;

/**
 * ScoreTrackingListener update the counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;
    private Counter currentBlocks;

    /**
     * Constructor.
     *
     * @param currentScore  the current score
     * @param currentBlocks the current blocks in the game
     */
    public ScoreTrackingListener(Counter currentScore, Counter currentBlocks) {
        this.currentScore = currentScore;
        this.currentBlocks = currentBlocks;
    }

    /**
     * return the current score.
     *
     * @return the current Score
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * return the current blocks.
     *
     * @return the current blocks.
     */
    public Counter getCurrentBlocks() {
        return this.currentBlocks;
    }

    /**
     * the method set the current score.
     *
     * @param currentScore the current score
     */
    public void setCurrentScore(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that being Hit
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.getCurrentBlocks().decrease(1);
        // if we got to the last block add 100 to the score
        if (this.getCurrentBlocks().getValue() == 0) {
            this.getCurrentScore().increase(100);
        } else {
            this.getCurrentScore().increase(5);
        }
    }
}
