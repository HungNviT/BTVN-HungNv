package touhou.players;

import bases.GameObject;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;

import javax.swing.*;
import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    private Animation animation;

    public PlayerSpell() {
        super();
        this.animation = new Animation(
                1,
                false,
                SpriteUtils.loadImage("assets/images/player-spells/a/0.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/1.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/2.png"),
                SpriteUtils.loadImage("assets/images/player-spells/a/3.png")
        );
        this.renderer = animation;
        this.boxCollider = new BoxCollider(20, 20);
        this.children.add(boxCollider);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
        deactiveIfNeeded();
    }

    public void deactiveIfNeeded() {
        if(this.screenPosition.y < 0){
            this.isActive = false;
        }
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider,  Enemy.class);
        if (enemy != null) {
            enemy.setActive(false);
            this.isActive = false;
        }
    }
    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }
    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
