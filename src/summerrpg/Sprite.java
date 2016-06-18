package summerrpg;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class Sprite {

    /*====================================*/
    /*---------------Fields---------------*/

    private BufferedImage[] image;
    private int imageNumber;
    private double width, height;
    private AffineTransform offset;

    /*====================================*/
    /*-------------Constructor------------*/

    Sprite(BufferedImage[] image) {
        this.image = image;
        width = image[0].getWidth();
        height = image[0].getHeight();
        offset = new AffineTransform();
        offset.translate(-width / 2, -height / 2);
        imageNumber = image.length;
    }

    /*====================================*/
    /*--------------Actions---------------*/

    void paint(Graphics g, double x, double y, double imageXScale, double imageYScale, double imageIndex) {
        Instance camera = InstanceManager.getPlayer();
        AffineTransform affineTransform = new AffineTransform(offset);
        affineTransform.translate(x - camera.getX() + GameFrame.WIDTH/2, y - camera.getY()+GameFrame.HEIGHT/2);
        affineTransform.scale(imageXScale, imageYScale);
        ((Graphics2D) g).drawImage(image[(int) imageIndex % imageNumber], affineTransform, null);
    }
}
