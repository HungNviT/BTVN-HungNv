package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;
import touhou.players.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by huynq on 8/9/17.
 */
public class Enemy extends GameObject implements PhysicsBody {
    private static final float SPEED = 3;
    private BoxCollider boxCollider;
    private boolean bulletLock;
    private FrameCounter coolDownCounter;
    private Animation animation;
    private InputManager inputManager;
    private float damage = 1;

    public Enemy() {
        super();
        this.bulletLock = false;
        this.animation = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
        );
        boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
        coolDownCounter = new FrameCounter(60);
        this.renderer = animation;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        shoot();
        castBullet();
        hitPlayer();

    }

    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.getHitPlayer(damage);
            this.isActive = true;
        }
    }

    private void castBullet() {
        if(!bulletLock) {
            EnemyBullet newBullet = GameObjectPool.recycle(EnemyBullet.class);
            newBullet.getPosition().set(this.position.add(0, 0));
            bulletLock = true;
            coolDownCounter.reset();
        }
        unLockBullet();

    }

    private void unLockBullet() {
        if (bulletLock) {
            if (coolDownCounter.run()) {
                bulletLock = false;
            }
        }
    }


    private void shoot() {
        // TODO: create enemy bullet and shoot
    }

    private void fly() {
        position.addUp(0, SPEED);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void getHit(int damage) {
        //TODO : decrease HP
        this.setActive(false);
        EnemyExplosion enemyExplosion = GameObjectPool.recycle(EnemyExplosion.class);
        enemyExplosion.getPosition().set(this.position);

    }
}


