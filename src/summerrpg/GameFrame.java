package summerrpg;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame implements Runnable{
    public static final int WIDTH = 960, HEIGHT = 640, FPS = 60;
    private static final long targetTime = 1000 / FPS;
    private boolean running = true, pause = false;
    
    public GameFrame() {
        super("SummerRPG");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(this).start();
    }
    
    public void run() {
        while(running) {
            long startTime = System.currentTimeMillis();

            if (!pause) InstanceManager.update();
            paint(getGraphics());

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

    public void paint(Graphics g) {
        super.paint(g);
        InstanceManager.paint(g);
    }
}
