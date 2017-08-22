package touhou.enemies.enemyBonus;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.players.Player;

public class BonusBullet1 extends GameObject {
    private BoxCollider boxCollider;
    private Animation animation;

    public BonusBullet1() {
        super();
        this.animation = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/cyan.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/white.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/yellow.png")
        );
        this.renderer = animation;
        this. boxCollider = new BoxCollider(10, 10);
        this.children.add(boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, 5);
        hitPlayer();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(screenPosition.y > 768){
            this.isActive = false;
        }
    }

    private void hitPlayer() {
        Player player = Physics.collideWith(boxCollider, Player.class);
        if(player != null){
            player.setActive(false);
            this.isActive = false;
        }
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
