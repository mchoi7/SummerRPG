package summerrpg;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameFrame extends JFrame implements Runnable{
    public static final int WIDTH = 960, HEIGHT = 640, FPS = 60;
    private static final long targetTime = 1000 / FPS;

    /*====================================*/
    /*---------------Fields---------------*/

    private boolean running, pause;

    /*====================================*/
    /*-------------Constructor------------*/

    public GameFrame() {
        super("SummerRPG");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        InputManager.register(this);
        createBufferStrategy(3);

        start();
    }

    private synchronized void start() {
        new Thread(this).start();
        running = true;
    }

    private synchronized void stop() {
        running = false;
    }

    /*====================================*/
    /*--------------Actions---------------*/

    public void run() {

        Instance player = new Instance(100, 100);
        player.setPlayer();
        Instance block = new Instance(150, 100);
        block.setBlock();

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
        if (!pause) InstanceManager.update();
    }

    private void render() {
        Graphics g = getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        InstanceManager.render(g);
        g.dispose();
        getBufferStrategy().show();
    }
}
