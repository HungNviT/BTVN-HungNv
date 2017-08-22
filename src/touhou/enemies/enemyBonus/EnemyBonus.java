package touhou.enemies.enemyBonus;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.players.Player;

public class EnemyBonus extends GameObject {
    private float SPEED = 1;
    private BoxCollider boxCollider;
    private Animation animation;
    private Constraints constraints;
    private boolean bulletLock;
    private FrameCounter coolDownCounter;

    public EnemyBonus() {
        super();
        this.animation = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/6.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/7.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/8.png")
        );
        this.boxCollider = new BoxCollider(30, 30);
        this.children.add(boxCollider);

        this.coolDownCounter = new FrameCounter(60);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        fly();
        castBullet();
        hitPlayer();
    }

    private void hitPlayer() {
        Player player = Physics.collideWith(boxCollider, Player.class);
        if(player != null){
            player.setActive(false);
            this.isActive = false;
        }
    }

    private void castBullet() {
        if(!bulletLock) {
            BonusBullet1 newBonusBullet1 = GameObjectPool.recycle(BonusBullet1.class);
            BonusBullet1 newBonusBullet2 = GameObjectPool.recycle(BonusBullet1.class);
            BonusBullet1 newBonusBullet3 = GameObjectPool.recycle(BonusBullet1.class);
            BonusBullet1 newBonusBullet4 = GameObjectPool.recycle(BonusBullet1.class);
            BonusBullet2 newBonusBullet5 = GameObjectPool.recycle(BonusBullet2.class);
            newBonusBullet1.getPosition().set(this.position.add(0, 0));
            newBonusBullet2.getPosition().set(this.position.add(20, 20));
            newBonusBullet3.getPosition().set(this.position.add(-20, 20));
            newBonusBullet4.getPosition().set(this.position.add(0, 40));
            newBonusBullet5.getPosition().set(this.position.add(0, 50));

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

    private void fly() {
        if(position.x == constraints.left){
            position.addUp(SPEED, 3 * SPEED);
            SPEED = +1;
        }
        else if (position.x == constraints.right){
            position.addUp(-SPEED, -SPEED);
            SPEED = -1;
        }
        else if(position.y == constraints.top){
            position.addUp(-SPEED, SPEED);
            SPEED = -1;
        }
        else if(position.y == constraints.bottom){
            position.addUp(SPEED, SPEED);
            SPEED = -1;
        }
        else {
            position.addUp(SPEED, -SPEED);
        }

        constraints.make(position);
    }
    public Vector2D getPosition(){
        return position;
    }
    public void setConstraints(Constraints constraints){
        this.constraints = constraints;
    }
}
