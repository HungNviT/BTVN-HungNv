package bases.physics;

import bases.GameObject;
import touhou.enemies.Enemy;

import java.util.Vector;

/**
 * Created by huynq on 8/12/17.
 */
public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();
    //Enemy, EnemyBullet, PlayerSpell..
    public Enemy collideWithEnemy(BoxCollider boxCollider) {
        for(PhysicsBody body : bodies) {
            if(body.isActive()) {
                if(body instanceof Enemy && body.getBoxCollider().intersects(boxCollider)) {
                    return (Enemy) body;
                }
            }
        }
        return null;
    }

//    public Player collideWithPlayer(BoxCollider boxCollider) {

    public static <T extends GameObject> T collideWith(BoxCollider boxCollider, Class<T> clazz) {
        for(PhysicsBody body : bodies) {
            if(body.isActive()) {
                if(body.getClass().equals(clazz) && body.getBoxCollider().intersects(boxCollider)) {
                    return (T) body;
                }
            }
        }
        return null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }
}
