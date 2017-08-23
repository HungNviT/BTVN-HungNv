package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject {
    private FrameCounter spawnCounter;
    private Random random;

    public EnemySpawner() {
        super();
        spawnCounter = new FrameCounter(70);
        random = new Random();
    }

    public void spawn() {
        if (spawnCounter.run()) {
            spawnCounter.reset();
//            Enemy enemy = new Enemy();//not pooling
            Enemy enemy = GameObjectPool.recycle(Enemy.class);// pooling
            enemy.getPosition().set(random.nextInt(384), 40);
        }
    }

}
