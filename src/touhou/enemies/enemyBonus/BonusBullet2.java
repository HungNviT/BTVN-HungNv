package touhou.enemies.enemyBonus;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;

public class BonusBullet2 extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private float damage = 1;

    public BonusBullet2() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/explosions/3.png"));
        this.boxCollider = new BoxCollider(30, 30);
        this.children.add(boxCollider);
        Physics.add(this);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, 6);
        hitPlayer();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded(){
        if(screenPosition.y > 768){
            this.isActive = false;
        }
    }

    private void hitPlayer() {
        Player player = Physics.collideWith(boxCollider, Player.class);
        if(player != null){
            player.getHitPlayer(damage);
            this.isActive = false;
        }
    }

    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
