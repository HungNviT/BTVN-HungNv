package touhou.players.spheres;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;
import touhou.enemies.enemyBonus.EnemyBonus;
import touhou.players.SpellFollow;

public class SphereSpell extends GameObject implements PhysicsBody{
    private BoxCollider boxCollider;
    private Animation animation;
    private int damage = 1;

    public SphereSpell() {
        super();
        this.animation = new Animation(
                7,
                false,
                false,
                SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png")
        );
        this.renderer = animation;
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.screenPosition.y < 0){
            this.isActive = false;
        }
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(boxCollider, Enemy.class);
        if(enemy != null){
            enemy.getHit(damage);
            this.isActive = false;
        }
        hitEnemyBonus();
    }

    private void hitEnemyBonus() {
        EnemyBonus enemyBonus = Physics.collideWith(boxCollider, EnemyBonus.class);
        if(enemyBonus != null){
            enemyBonus.getHit(damage);
            this.isActive = false;
        }
    }


    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }
    @Override
    public BoxCollider getBoxCollider() {
        return null;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

}
