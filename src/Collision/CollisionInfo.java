package Collision;

import Shapes.Point;

/**
 * the class represent the collision info of the collision point.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     *
     * @param point collision point
     * @param coll  collision object
     */
    public CollisionInfo(Point point, Collidable coll) {
        this.collisionPoint = point;
        this.collisionObject = coll;
    }

    /**
     * the method return the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the method return the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
