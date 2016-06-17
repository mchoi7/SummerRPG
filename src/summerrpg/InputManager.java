package summerrpg;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private static InputManager res = new InputManager();

    private boolean[] input;

    public static boolean[] getInput() {
        return res.input;
    }

    private InputManager() {
        input = new boolean[256];
    }

    public static void register(JFrame frame) {
        frame.addKeyListener(res);
    }
    public void keyPressed(KeyEvent e) {
        input[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        input[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {
    }

}
