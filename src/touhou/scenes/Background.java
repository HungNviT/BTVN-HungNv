package touhou.scenes;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.settings.Settings;

public class Background extends GameObject {
    private ImageRenderer imageRender;
    private final float SPEED = 1;
    private final float imageHeight;
    public Background(){
        super();
        this.imageRender = new ImageRenderer(
                SpriteUtils.loadImage("assets/images/background/0.png")
        );
        this.imageRender.getAnchor().set(0, 1);
        this.position.set(0, Settings.instance.getGamePlayHeight());
        this.imageHeight = imageRender.image.getHeight();
        this.renderer = imageRender;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.y += SPEED;
        if(position.y > imageHeight){
            position.y = imageHeight;
        }
    }
}
