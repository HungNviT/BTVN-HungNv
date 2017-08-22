package touhou.settings;

import java.awt.*;

public class Settings {
    private int windowWidth;
    private int windowHeight;

    private int gamePlayWidth;
    private int getGamePlayHeight;

    private Insets windowInsets;

    public static final Settings instance =  new Settings();

    public Settings(int windowWidth, int windowHeight, int gamePlayWidth, int getGamePlayHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gamePlayWidth = gamePlayWidth;
        this.getGamePlayHeight = getGamePlayHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getGamePlayWidth() {
        return gamePlayWidth;
    }

    public int getGamePlayHeight() {
        return getGamePlayHeight;
    }
    public void setWindowInsets(Insets windowInsets){
        this.windowInsets = windowInsets;
    }
    public Insets getWindowInsets(){
        return windowInsets;
    }
    private Settings(){
        this(1024, 768, 384, 768);
    }
}
