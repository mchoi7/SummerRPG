package summerrpg;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameFrame extends JFrame implements Runnable, KeyListener {
    public static final int WIDTH = 960, HEIGHT = 640, FPS = 60;
    private static final long targetTime = 1000 / FPS;

    /*====================================*/
    /*---------------Fields---------------*/

    private boolean running, pause;
    private boolean[] key = new boolean[128];

    /*====================================*/
    /*-------------Constructor------------*/

    private GameFrame() {
        super("SummerRPG");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        addKeyListener(this);
        createBufferStrategy(3);

        start();
    }

    /*====================================*/
    /*--------------Actions---------------*/

    public static void main(String[] args) {
        new GameFrame();
    }

    public void run() {

        LevelManager.loadLevel("levelOne");

        while(running) {
            long startTime = System.currentTimeMillis();

            update();
            render();

            long sleepTime = targetTime - System.currentTimeMillis() + startTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Sleep error: " + e.getMessage());
                }
            }
        }
    }

    private void update() {
        if(InstanceManager.getPlayer() != null) InstanceManager.getPlayer().setKey(key);
        if (!pause) InstanceManager.update();
    }

    private void render() {
        Graphics g = getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        InstanceManager.render(g);
        g.dispose();
        getBufferStrategy().show();
    }

    private synchronized void start() {
        new Thread(this).start();
        running = true;
    }

    private synchronized void stop() {
        running = false;
    }

    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {
    }
}
