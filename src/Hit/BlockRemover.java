package Hit;

import Collision.Block;
import GameMain.GameLevel;
import Sprites.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel       the game
     * @param remainingBlocks the number of the blocks in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * the method return the game.
     *
     * @return the game
     */
    public GameLevel getGame() {
        return this.gameLevel;
    }

    /**
     * the method return the number of the blocks that remaining in the game.
     *
     * @return the number of the blocks that remaining in the game.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * the method set the number of the blocks that remaining in the game.
     *
     * @param remainingBlocks the number of the blocks that remaining in the game.
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * the method also remove this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that being Hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.getGame().removeCollidable(beingHit);
        this.getGame().removeSprite(beingHit);
        this.getRemainingBlocks().decrease(1);

    }
}
