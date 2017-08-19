package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.inputs.InputManager;
import touhou.players.SpellFollow;

public class PlayerSphere extends GameObject {
    Animation animation;
    private InputManager inputManager;
    private boolean spellLock;
    private FrameCounter coolDownCounter;

    //ban than thg PlayerSphere ko render
    //Animation animation >> render anh
    public PlayerSphere() {
        super();
        this.spellLock = false;
        this.animation = new Animation(
                7, false,
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png")
        );
        this.renderer = animation;
        coolDownCounter = new FrameCounter(3);
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        castSpell();
    }

    private void castSpell() {
        if(inputManager.xPressed && !spellLock){
            SphereSpell sphereSpell = GameObjectPool.recycle(SphereSpell.class);
            sphereSpell.getPosition().set(this.screenPosition.add(0, -30));
            spellLock = true;
            coolDownCounter.reset();
        }
        unLockSpell();
    }

    private void unLockSpell() {
        if(coolDownCounter.run()){
            spellLock = false;
        }
    }

    //tac dong xuong animation
    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }
}
