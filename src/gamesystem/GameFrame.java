package gamesystem;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable{
    public static final int WIDTH = 500, HEIGHT = 500, FPS = 60;
    public static final String TITLE = "SummerRPG";
    boolean running = false;
    
    public GameFrame() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    public void run() {
        long startTime = System.currentTimeMillis();
        update();
        render();
        long elapsedTime = startTime - System.currentTimeMillis(); 
        long targetTime = 1000/FPS; // targetTime of each frame is 1/FPS'th of a second
        long sleepTime = targetTime - elapsedTime;
        if(sleepTime > 0) { // if there is time left to sleep in a frame, sleep the difference until next frame
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Sleep error: " + e.getMessage());
            }
        }
    }
    
    public void update() {
        InstanceManager.res.update();
    }
    
    public void render() {
        InstanceManager.res.render();
    }
}
