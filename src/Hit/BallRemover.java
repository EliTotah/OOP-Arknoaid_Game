package Hit;

import Collision.Block;
import GameMain.GameLevel;
import Sprites.Ball;

/**
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param gameLevel      the game
     * @param remainingBalls the number of the balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
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
     * the method return the number of the balls that remaining in the game.
     *
     * @return the number of the balls that remaining in the game.
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * the method set the number of the balls that remaining in the game.
     *
     * @param remainingBalls the number of the balls that remaining in the game.
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    /**
     * balls that are fall should be removed from the game.
     * the method also remove this listener from the ball that is being removed from the game.
     *
     * @param beingHit the block that being Hit
     * @param hitter   the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.getGame().removeSprite(hitter);
        this.getRemainingBalls().decrease(1);

    }
}
