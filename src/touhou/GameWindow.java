package touhou;

import bases.GameObject;
import tklibs.SpriteUtils;
import bases.Constraints;
import touhou.enemies.EnemySpawner;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.spheres.PlayerSphere;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    private BufferedImage background;

    Player player = new Player();
    EnemySpawner enemySpawner = new EnemySpawner(); // TODO: Viec cua lop: sua thanh game object

    InputManager inputManager = new InputManager(); //inputmanager cua ca game

    public GameWindow() {
        pack();
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        addPlayer();
        setupGameLoop();
        setupWindow();
    }

    private void addPlayer() {
        player.setInputManager(this.inputManager); //player cos children la playersphere
        player.setContraints(new Constraints(getInsets().top, 768, getInsets().left, 384));
        player.getPosition().set(384 / 2, 580);

        for(GameObject gameObject : player.getChildren()) {
            if(gameObject instanceof PlayerSphere) {
                PlayerSphere playerSphere = (PlayerSphere) gameObject;
                playerSphere.setInputManager(this.inputManager);
            }
        }
        GameObject.add(player);
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        GameObject.runAll();
        enemySpawner.spawn();
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backbufferImage, 0, 0, null);
    }

    private void render() {

        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        backbufferGraphics.drawImage(background, 0, 0, null);

        GameObject.renderAll(backbufferGraphics);

        repaint(); // ask to repaint
    }
}
