package GameMain.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * the class represent Key Press Stoppable Animation, implements Animation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Consrtuctor.
     *
     * @param sensor    KeyboardSensor
     * @param key       the key that we need to press
     * @param animation the animation we run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * .
     * the method run one frame of the game
     *
     * @param d DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // do one frame of the animation
        this.animation.doOneFrame(d);
        // if the user pressed on the key
        if (this.sensor.isPressed(this.key)) {
            // check if the key were already pressed
            if (!this.isAlreadyPressed) {
                this.stop = true;
                return;
            }
            this.isAlreadyPressed = false;
        }
    }

    /**
     * the method check if the game should stop.
     *
     * @return boolean - true if we need to stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
