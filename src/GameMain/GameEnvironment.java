package GameMain;

import Collision.Collidable;
import Collision.CollisionInfo;
import Shapes.Line;
import Shapes.Point;
import Shapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * the class represent the GameMain.Game Environment, the list the collidable objects in the game.
 */
public class GameEnvironment {

    private List<Collidable> collidableList;

    /**
     * Constructor.
     *
     * @param list collidable list
     */
    public GameEnvironment(List<Collidable> list) {
        this.collidableList = list;
    }

    /**
     * default Constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.getCollidableList().add(c);
    }

    /**
     * remove the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        this.getCollidableList().remove(c);
    }

    /**
     * .
     * Return the collidable list
     *
     * @return the collidable list
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * the method check if the object collide with any of the collidables and return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the Shapes.Line that the ball will pass if will not be any collision
     * @return return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int index = 0;
        double dis = 2000000000;
        Point closetPoint = null;
        // Make a copy of the hitListeners before iterating over them.
        List<Collidable> collidables = new ArrayList<>(this.getCollidableList());
        for (int i = 0; i < collidables.size(); i++) {
            Rectangle rect = collidables.get(i).getCollisionRectangle();
            Point p = trajectory.closestIntersectionToStartOfLine(rect);
            if (p != null) {
                // check if the Intersection point is little that the minimum and if yes save it
                if (p.distance(trajectory.start()) < dis) {
                    dis = p.distance(trajectory.start());
                    index = i;
                    closetPoint = p;
                }
            }
        }
        if (closetPoint == null) {
            return null;
        }
        return new CollisionInfo(closetPoint, this.getCollidableList().get(index));
    }
}
