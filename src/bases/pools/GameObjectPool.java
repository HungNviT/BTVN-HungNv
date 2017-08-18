package bases.pools;

import bases.GameObject;
import bases.Vector2D;
import touhou.players.Player;
import touhou.players.PlayerSpell;
import touhou.players.spheres.SphereSpell;

import java.util.Vector;

public class GameObjectPool {
    private static Vector<GameObject> pool = new Vector<>();
    //Enemy, EnemyBullet, PlayerSpell..
    //lay object tu pool ra
    //Enemy recycleEnemy()
    //Player recyclePlayer()
    public static <T extends GameObject> T recycle(Class<T> classz) {
        for (GameObject gameObject : pool) {
            if (gameObject.getClass().equals(classz)) {
                // getClass().equals(classz) kiem tra xem cai gameObject hien tai co phai la kieu classz k
                if (!gameObject.isActive()) {
                    gameObject.setActive(true);
                    return (T) gameObject;
                }
            }

        }

        try {
            T newGameObject = classz.newInstance();
            GameObject.add(newGameObject);
            pool.add(newGameObject);
            return newGameObject;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}