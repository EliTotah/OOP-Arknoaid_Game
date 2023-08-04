package GameMain.Animations;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private static final int COUNT_FROM = 3;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private boolean first;

    /**
     * Constructor.
     *
     * @param numOfSeconds num of second that the countdown lasts
     * @param countFrom    the number that we start the countdown
     * @param gameScreen   our game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.first = true;
    }

    /**
     * the method return the num of seconds that the countdown lasts.
     *
     * @return the num of seconds that the countdown lasts.
     */
    public double getNumOfSeconds() {
        return this.numOfSeconds;
    }

    /**
     * the method return the number that we start the countdown.
     *
     * @return the number that we start the countdown
     */
    public int getCountFrom() {
        return this.countFrom;
    }

    /**
     * the method return our game screen.
     *
     * @return our game screen
     */
    public SpriteCollection getGameScreen() {
        return this.gameScreen;
    }

    /**
     * the method set the number that we start the countdown.
     *
     * @param countFrom the number that we start the countdown
     */
    public void setCountFrom(int countFrom) {
        this.countFrom = countFrom;
    }

    /**
     * the method set if we need to stop the animation run.
     *
     * @param stop boolean
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * the method return if we at our first number.
     *
     * @return boolean
     */
    public boolean isFirst() {
        return this.first;
    }

    /**
     * the method set if we at our first number.
     *
     * @param first boolean
     */
    public void setFirst(boolean first) {
        this.first = first;
    }

    /**
     * .
     * the method run one frame of the game
     *
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        // if we are in our first digit
        if (this.isFirst()) {
            // draw all the objects that in the game
            this.getGameScreen().drawAllOn(d);
            this.setFirst(false);
            // draw the first digit
            d.drawText(370, 60, " " + this.getCountFrom(), 35);
            return;
        }
        // time of the countdown
        double time = (1000 * this.getNumOfSeconds()) / COUNT_FROM;
        // draw all the objects that in the game
        this.getGameScreen().drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        d.setColor(Color.WHITE);
        // draw the digits
        d.drawText(370, 80, " " + this.getCountFrom(), 50);
        // Pause the display of the number
        if (this.getCountFrom() != COUNT_FROM) {
            sleeper.sleepFor((long) time);
        }
        // subtract the countdown in 1
        this.setCountFrom(this.getCountFrom() - 1);
        // if we got to zero - stop the countdown
        if (this.getCountFrom() == -1) {
            this.setStop(true);
        }
    }

    /**
     * the method check if the game should stop.
     *
     * @return boolean - true if we need to stop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
