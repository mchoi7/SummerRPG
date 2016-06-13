/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package summerrpg;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
/**
 *
 * @author michaelchoi
 */
public class GamePanel extends JPanel {
    private static int WIDTH = 500, HEIGHT = 500;
    public void paint(Graphics g) {
        setSize(WIDTH, HEIGHT);
    }
}
