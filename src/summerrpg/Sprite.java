package summerrpg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
    BufferedImage[] images;
    public Sprite(BufferedImage[] images) {
        this.images = images;
    }
    
    public void paint(Graphics g, double x, double y, double imageIndex) {
        g.drawImage(images[(int) imageIndex], (int) x, (int) y, null);
    }
}
