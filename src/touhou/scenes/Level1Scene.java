package touhou.scenes;

import bases.Constraints;
import bases.GameObject;
import bases.physics.Physics;
import touhou.enemies.enemyBonus.EnemyBonus;
import touhou.inputs.InputManager;
import touhou.enemies.EnemySpawner;
import touhou.players.Player;
import touhou.players.spheres.PlayerSphere;
import touhou.settings.Settings;

import java.awt.image.BufferedImage;

public class Level1Scene {
    private BufferedImage background;
    Player player = new Player();
    EnemySpawner enemySpawner = new EnemySpawner(); // TODO: Viec cua lop: sua thanh game object
    EnemyBonus enemyBonus = new EnemyBonus();
     Settings settings = Settings.instance;
    public void init(){
        addBackground();
        addPlayer();
        addEnemyBonus();
        addEnemy();
    }

    private void addEnemy() {
        enemySpawner.spawn();
    }

    private void addEnemyBonus() {
        enemyBonus.setConstraints( new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight() / 3,
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
        );
        GameObject.add(enemyBonus);
        enemyBonus.getPosition().set(settings.getGamePlayWidth()  / 2, settings.getWindowInsets().top);

    }


    private void addBackground() {
        GameObject.add(new Background());
    }


    private void addPlayer() {
       player.setInputManager(InputManager.instance); //player cos children la playersphere
        player.setContraints(new Constraints(
                settings.getWindowInsets().top,
                settings.getGamePlayHeight(),
                settings.getWindowInsets().left,
                settings.getGamePlayWidth())
        );
        player.getPosition().set(
                settings.getGamePlayWidth() / 2,
                settings.getGamePlayHeight() * 2 / 3);

        for(GameObject gameObject : player.getChildren()) {
            if(gameObject instanceof PlayerSphere) {
                PlayerSphere playerSphere = (PlayerSphere) gameObject;
                playerSphere.setInputManager(InputManager.instance);
            }
        }

        GameObject.add(player);
        Physics.add(player);
    }

}
