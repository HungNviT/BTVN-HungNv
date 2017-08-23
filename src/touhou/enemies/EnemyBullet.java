package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;

import java.awt.image.BufferedImage;
import java.util.List;

public class EnemyBullet extends GameObject implements PhysicsBody {

    private BoxCollider boxCollider;
    private Animation animation;
    private float damage = 1;

    public EnemyBullet() {
        super();
        this.animation = new Animation(
                7,
                false,
                false,
                SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/cyan.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/white.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/yellow.png")
        );
        this.renderer = animation;
        this.boxCollider = new BoxCollider(10, 10);
        this.children.add(boxCollider);


    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(0, 3);
        hitPlayer();
        deactiveIfNeeded();
    }

    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if (player != null) {
            player.getHitPlayer(damage);
            this.isActive = false;
        }
    }


    private void deactiveIfNeeded() {
        if(this.screenPosition.y > 768){
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
