package summerrpg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {

    /*====================================*/
    /*---------------Fields---------------*/

    private BufferedImage[] image;
    private int xOffset, yOffset, imageNumber;

    /*====================================*/
    /*-------------Constructor------------*/

    public Sprite(BufferedImage[] image) {
        this.image = image;
        xOffset = image[0].getWidth() / 2;
        yOffset = image[0].getHeight() / 2;
        imageNumber = image.length;
    }

    /*====================================*/
    /*--------------Actions---------------*/

    public void paint(Graphics g, double x, double y, double imageIndex) {
        g.drawImage(image[(int) imageIndex % imageNumber], (int) x - xOffset, (int) y - yOffset, null);
    }
}
