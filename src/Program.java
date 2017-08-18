import bases.GameObject;
import bases.physics.BoxCollider;
import bases.pools.GameObjectPool;
import touhou.GameWindow;
import touhou.enemies.Enemy;


/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.loop();
    }
}