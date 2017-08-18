package bases.renderers;

import bases.FrameCounter;
import bases.Vector2D;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.*;

public class Animation implements Renderer {

    private List<BufferedImage> images;
    //list nhieu anh
    //!reverse 0 >> n (4 cai anh 0, 1, 2, 3)
    //reverse n >> 0 (3, 2, 1, 0)
    private FrameCounter frameCounter;
    private  int currentImageIndex;
    private boolean reverse; //true false
    //if(reverse == true) n >> 0 //dao nguoc
    //if(!reverse) if(reverse == false) if(!false == true) 0 >> n

    public Animation(int frameDelay, boolean reverse, BufferedImage... images){
        this.frameCounter = new FrameCounter(frameDelay);
        this.images = Arrays.asList(images);
        this.currentImageIndex = 0;
        this.reverse = reverse;
    }

    public Animation(BufferedImage... images){
        this(12, false, images);
    }
    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        BufferedImage image = images.get(currentImageIndex);
        Vector2D renderPosition = position.subtract(
                image.getWidth() / 2,
                image.getHeight() / 2);
        g2d.drawImage(image, (int)renderPosition.x, (int)renderPosition.y, null );
        updateCurrentImage();
    }

    private void updateCurrentImage() {
        if(frameCounter.run()){
            frameCounter.reset();
            if(!reverse) {
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    currentImageIndex = 0;
                }
            }else {
                currentImageIndex --;
                if(currentImageIndex < 0){
                    currentImageIndex = images.size() - 1;
                }
            }
        }
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}
