package GameMain.Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * the class represent animation runner object.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     *
     * @param gui             the gui
     * @param framesPerSecond amount frame per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Constructor.
     *
     * @param gui the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     * the method return the gui.
     *
     * @return the gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * .
     * the method set the gui of the animation runner
     *
     * @param gui the gui.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * the method return the amount of the frames per second.
     *
     * @return the amount of the frames per second.
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * the method set the amount of the frames per second.
     *
     * @param framesPerSecond the amount of the frames per second.
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * the method Run the game -- start the animation loop.
     *
     * @param animation the animation that we want to run
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.getFramesPerSecond();
        // check if we need to stop
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.getGui().getDrawSurface();

            animation.doOneFrame(d);

            this.getGui().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
