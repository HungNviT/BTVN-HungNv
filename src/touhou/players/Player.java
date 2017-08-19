package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.pools.GameObjectPool;
import tklibs.SpriteUtils;
import bases.Constraints;
import bases.FrameCounter;
import bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;
import touhou.inputs.InputManager;
import touhou.players.spheres.PlayerSphere;

/**
 * Created by huynq on 8/2/17.
 */
public class Player extends GameObject {
    private static final int SPEED = 5;

    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    private BoxCollider boxCollider;

    public Player() {
        super();
        this.spellLock = false;
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        this.coolDownCounter = new FrameCounter(3);
        this.boxCollider = new BoxCollider(30, 30);
        addSpheres();
    }

    private void addSpheres() {
        //tao ra 2 sphere
        PlayerSphere leftSphere = new PlayerSphere();
        leftSphere.getPosition().set(-20, 0);
        leftSphere.setReverse(false);

        PlayerSphere rightSphere = new PlayerSphere();
        rightSphere.getPosition().set(20, 0);
        rightSphere.setReverse(true);

        this.children.add(leftSphere);
        this.children.add(rightSphere);

    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPostion) {
        super.run(parentPostion);

        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed)
            position.addUp(-SPEED, 0);
        if (inputManager.rightPressed)
            position.addUp(SPEED, 0);

        if (constraints != null) {
            constraints.make(position);
        }

        castSpell();
    }



    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = GameObjectPool.recycle(SpellFollow.class);
            newSpell.getPosition().set(this.position.add(0, -30));

            spellLock = true;
            coolDownCounter.reset();
        }

        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
