package summerrpg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {

    /*====================================*/
    /*---------------Fields---------------*/

    private BufferedImage[] images;
    private int imageNumber;

    /*====================================*/
    /*-------------Constructor------------*/

    public Sprite(BufferedImage[] images) {
        this.images = images;
        imageNumber = images.length;
    }

    /*====================================*/
    /*--------------Actions---------------*/

    public void paint(Graphics g, double x, double y, double imageIndex) {
        g.drawImage(images[(int) imageIndex % imageNumber], (int) x, (int) y, null);
    }
}
