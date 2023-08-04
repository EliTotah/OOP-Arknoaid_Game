package GameMain;

import Collision.Block;
import Collision.Collidable;
import Collision.Paddle;
import GameMain.Animations.AnimationRunner;
import GameMain.Animations.Animation;
import GameMain.Animations.PauseScreen;
import GameMain.Animations.KeyPressStoppableAnimation;
import GameMain.Animations.CountdownAnimation;
import GameMain.Levels.WideEasy;
import Hit.BallRemover;
import Hit.BlockRemover;
import Hit.ScoreTrackingListener;
import Shapes.Point;
import Shapes.Rectangle;
import Sprites.Ball;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import Hit.Counter;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * the class represent our game.
 */
public class GameLevel implements Animation {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int FRAME_PER_SECOND = 60;
    private static final int BORDER_WIDTH = 25;
    private static final int BORDER_HEIGHT = 25;
    private static final int MAX_NUM_COLOR = 255;
    private static final int DEFAULT_SIZE = 5;
    private static final int UPPER_LIMIT = 2;
    private static final int PADDLE_HEIGHT = 20;

    private static final int NUM_OF_SECONDS = 2;
    private static final int COUNT_FROM = 3;

    private GUI gui;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private biuoop.KeyboardSensor keyboard;
    private Sleeper sleeper;
    private AnimationRunner runner;
    private boolean running;
    private Counter lives;
    private boolean dead;
    private LevelInformation levelInformation;

    private BallRemover ballRemover;
    private Paddle paddle;

    /**
     * Constructor.
     *
     * @param sprites       list of sprites
     * @param environment   list of collidable
     * @param counterBalls  num of balls
     * @param counterBlocks num of blocks
     * @param score         the score of the player
     * @param li            the level information
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment,
                     Counter counterBlocks, Counter counterBalls, Counter score, LevelInformation li) {
        this.sprites = sprites;
        this.environment = environment;
        this.counterBlocks = counterBlocks;
        this.counterBalls = counterBalls;
        this.score = score;
        this.gui = new GUI("Random Circles Example", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.keyboard = gui.getKeyboardSensor();
        this.runner = new AnimationRunner(this.gui, FRAME_PER_SECOND);
        this.sleeper = new Sleeper();
        this.levelInformation = li;
        this.dead = false;
    }

    /**
     * default constructor.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter();
        this.counterBalls = new Counter();
        this.score = new Counter();
        this.gui = new GUI("Random Circles Example", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.keyboard = gui.getKeyboardSensor();
        this.runner = new AnimationRunner(this.gui, FRAME_PER_SECOND);
        this.sleeper = new Sleeper();
        this.dead = false;
    }

    /**
     * Constsructor.
     *
     * @param li the level information.
     */
    public GameLevel(LevelInformation li) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter();
        this.counterBalls = new Counter();
        this.score = new Counter();
        this.gui = new GUI("Random Circles Example", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.keyboard = gui.getKeyboardSensor();
        this.runner = new AnimationRunner(this.gui, FRAME_PER_SECOND);
        this.sleeper = new Sleeper();
        this.levelInformation = li;
        this.dead = false;
    }

    /**
     * Constuctor.
     *
     * @param li              the level information
     * @param gui             the gui
     * @param keyboardSensor  keyboard sensor
     * @param animationRunner the animation runner
     * @param score           the score of the user
     * @param lives           the lices
     */

    public GameLevel(LevelInformation li, GUI gui, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter();
        this.counterBalls = new Counter();
        this.score = score;
        this.sleeper = new Sleeper();
        this.gui = gui;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.levelInformation = li;
        this.lives = lives;
        this.dead = false;
    }

    /**
     * .
     * the method return the lives of the user.
     *
     * @return the lives of the user.
     */
    public Counter getLives() {
        return lives;
    }

    /**
     * the method return the lives of the user.
     *
     * @param lives the lives of the user.
     */
    public void setLives(Counter lives) {
        this.lives = lives;
    }

    /**
     * the method return the level information.
     *
     * @return the level information.
     */
    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * .
     * the method return the sprites
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * .
     * the method set empty sprites list.
     */
    public void setSprites() {
        this.sprites = new SpriteCollection();
    }

    /**
     * .
     * the method set empty game environment list.
     */
    public void setEnvironment() {
        this.environment = new GameEnvironment();
    }

    /**
     * .
     * the method set empty counterBlocks.
     */
    public void setCounterBlocks() {
        this.counterBlocks = new Counter();
    }

    /**
     * .
     * the method set empty counterBalls.
     */
    public void setCounterBalls() {
        this.counterBalls = new Counter();
    }

    /**
     * .
     * the method return the game environment
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * .
     * the method return the counter of blocks
     *
     * @return the counter of blocks
     */
    public Counter getCounterBlocks() {
        return counterBlocks;
    }

    /**
     * .
     * the method return the counter of balls
     *
     * @return the counter of balls
     */
    public Counter getCounterBalls() {
        return counterBalls;
    }

    /**
     * .
     * the method return the score
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * .
     * the method add collidable object to the list of the collidable
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.getEnvironment().addCollidable(c);
    }

    /**
     * .
     * the method add sprite object to the list of the sprites
     *
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        this.getSprites().addSprite(s);
    }

    /**
     * .
     * the method remove collidable object to the list of the collidable
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        this.getEnvironment().removeCollidable(c);
    }

    /**
     * .
     * the method remove sprite object to the list of the sprites
     *
     * @param s sprite object
     */
    public void removeSprite(Sprite s) {
        this.getSprites().removeSprite(s);
    }

    /**
     * the method return the keyboard sensor.
     *
     * @return the keyboard sensor.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }


    /**
     * the method return the gui of the game.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * the method return the sleeper.
     *
     * @return the sleeper.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * the method return the animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }

    /**
     * the method set the animation runner.
     *
     * @param runner the animation runner.
     */
    public void setRunner(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * the method return if we still need to run.
     *
     * @return boolean
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * the method return if the user dead or not.
     *
     * @return boolean
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * the method set if we still need to run.
     *
     * @param running boolean
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * the method Initialize a new game: create the Blocks and Sprite.Sprite.
     * Ball (and Collision.Paddle) and add them to the game.
     */
    public void initialize() {
        DrawSurface d = this.getGui().getDrawSurface();
        // draw the background of the level to the sprite list
        this.getSprites().addSprite(this.getLevelInformation().getBackground());
        // create the paddle
        createPaddle();
        // create the limits of the screen
        Block[] blocks = createLimits(d);
        // create the ball remover
        this.ballRemover = new BallRemover(this, this.getCounterBalls());
        // add the ball remover as Hit listener of the bottom limit
        blocks[UPPER_LIMIT].addHitListener(this.ballRemover);
        // add the screen limits to the game environment
        for (Block b : blocks) {
            b.addToGame(this);
        }
        // create the blocks of the game
        creatBlocks();
        this.ballRemover.getRemainingBalls().increase(this.getLevelInformation().numberOfBalls());
    }

    /**
     * .
     * the method create the Paddle by his position in accordance the level information
     */
    private void createPaddle() {
        DrawSurface d = this.getGui().getDrawSurface();
        Point p;
        // if we in the second level arrange the start position of the paddle
        if (this.getLevelInformation().levelName().equals("Wide Easy")) {
            p = new Shapes.Point(100, d.getHeight() - PADDLE_HEIGHT - BORDER_HEIGHT);
            // the start position of the paddle
        } else {
            p = new Shapes.Point(350, d.getHeight() - PADDLE_HEIGHT - BORDER_HEIGHT);
        }
        // create the rectangle of the paddle
        Rectangle r = new Shapes.Rectangle(p, this.getLevelInformation().paddleWidth(), PADDLE_HEIGHT);
        // create the paddle
        Paddle paddle = new Paddle(this.getKeyboard(), r, new Color(253, 190, 1),
                this.getLevelInformation().paddleSpeed());
        // add the paddle to the game environment
        paddle.addToGame(this);
        // set the game as the game environment of the paddle
        paddle.setGame(this);
        this.paddle = paddle;
    }

    /**
     * .
     * the method create the BallsOnTopOfPaddle by their position in accordance the level information
     */
    private void createBallsOnTopOfPaddle() {
        List<Point> pointsList = this.getLevelInformation().startingPointsOfBalls();
        for (int i = 0; i < pointsList.size(); i++) {
            // create the balls of the game
            Ball ball = new Ball((int) pointsList.get(i).getX(), (int) pointsList.get(i).getY(),
                    DEFAULT_SIZE, Color.white);
            // set the game as the game environment of the ball
            ball.setGameEnvironment(this.environment);
            // set the velocity of the ball by the given velocities of the level information
            ball.setVelocity(this.getLevelInformation().initialBallVelocities().get(i));
            // add the ball to the game environment
            ball.addToGame(this);
        }
    }

    /**
     * the method create and add to the game blocks by their position in accordance the level information.
     */
    private void creatBlocks() {
        // block remover
        BlockRemover blockRemover = new BlockRemover(this, this.getCounterBlocks());
        Rectangle r2 = new Rectangle(new Point(0, 0), SCREEN_WIDTH, 20);
        Block scb = new Block(r2, Color.white);
        // create score Tracking Listener
        ScoreTrackingListener stl = new ScoreTrackingListener(this.getScore(), new Counter());
        // create score indicator
        ScoreIndicator si = new ScoreIndicator(scb, stl);
        // add the score indicator to the game environment
        si.addToGame(this);
        for (Block block : this.getLevelInformation().blocks()) {
            // add the block to the game
            block.addToGame(this);
            // add the block remover as Hit listener of each block
            block.addHitListener(blockRemover);
            // add the score indicator as Hit listener of each block
            block.addHitListener(si.getStl());
            // add the counter of the block remover in 1
            blockRemover.getRemainingBlocks().increase(1);
            // add the counterBlocks of the score indicator in 1
            si.getStl().getCurrentBlocks().increase(1);
        }
        // add the block of the level name and the lives to the sprites
        Sprite name = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.drawText(570, 15, "Level Name: " + getLevelInformation().levelName(), 15);
                d.drawText(140, 15, "Lives: " + getLives().getValue(), 15);
            }

            @Override
            public void timePassed() {

            }

            @Override
            public void addToGame(GameLevel g) {

            }
        };
        this.addSprite(name);
    }

    /**
     * .
     * the method create the limits and the borders of the screen
     *
     * @param d Drawsurface
     * @return array of blocks
     */
    public Block[] createLimits(DrawSurface d) {
        Block[] blocks = new Block[4];
        // upper limit
        Shapes.Rectangle r1 = new Shapes.Rectangle(new Shapes.Point(0, 0), d.getWidth(), BORDER_HEIGHT);
        blocks[0] = new Block(r1, Color.gray);
        // left limit
        Shapes.Rectangle r2 = new Shapes.Rectangle(new Shapes.Point(0, BORDER_HEIGHT), BORDER_WIDTH, d.getHeight());
        blocks[1] = new Block(r2, Color.gray);
        // bottom limit
        Shapes.Rectangle r3 = new Shapes.Rectangle(new Point(BORDER_WIDTH, d.getHeight()),
                d.getWidth(), BORDER_HEIGHT);
        blocks[2] = new Block(r3, Color.gray);
        // right limit
        Shapes.Rectangle r4 = new Shapes.Rectangle(new Shapes.Point(d.getWidth() - BORDER_WIDTH, BORDER_HEIGHT),
                BORDER_WIDTH, d.getHeight());
        blocks[3] = new Block(r4, Color.gray);
        return blocks;
    }

    /**
     * .
     * the method create random color
     *
     * @return random color
     */
    private Color randColor() {
        Random rand = new Random();
        int a = rand.nextInt(MAX_NUM_COLOR);
        int b = rand.nextInt(MAX_NUM_COLOR);
        int c = rand.nextInt(MAX_NUM_COLOR);
        return new Color(a, b, c);
    }

    /**
     * the method check if the game should stop.
     *
     * @return boolean - true if we need to stop
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * .
     * the method run one frame of the game
     *
     * @param d DrawSurface
     */
    public void doOneFrame(DrawSurface d) {
        this.getSprites().notifyAllTimePassed();
        this.getSprites().drawAllOn(d);
        // stopping condition
        if (this.getCounterBalls().getValue() == 0) {
            // decrease the lives in 1
            this.setLives(new Counter(this.getLives().getValue() - 1));
            if (this.getLives().getValue() != 0) {
                // create new balls
                this.createBallsOnTopOfPaddle();
                // add them to ball remover
                this.ballRemover.getRemainingBalls().increase(this.getLevelInformation().numberOfBalls());
                // remove the old paddle from the game
                this.paddle.removeFromGame(this);
                // create new paddle
                createPaddle();
                this.getSprites().drawAllOn(d);
                // countdown
                this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.getSprites()));
            }
        }
        // if there is no lives anymore so the user dead
        if (this.getLives().getValue() == 0) {
            setRunning(false);
        }
        // if we removed all the blocks
        if (this.getCounterBlocks().getValue() == 0) {
            setRunning(false);
        }
        // if we pressed on "p" key - pause the game
        if (this.getKeyboard().isPressed("p")) {
            this.getRunner().run(new KeyPressStoppableAnimation(this.getKeyboard(),
                    "space", new PauseScreen(this.getKeyboard())));
        }
    }

    /**
     * the method Run the game -- start the animation loop.
     */
    public void run() {
        // create the ball
        this.createBallsOnTopOfPaddle();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites));
        this.setRunning(true);
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.getRunner().run(this);
        this.getSleeper().sleepFor(300);
    }

    /**
     * the method initialize and run the game.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        LevelInformation directHit = new WideEasy();
        GameLevel level = new GameLevel(directHit);
        level.initialize();
        level.run();
    }
}
