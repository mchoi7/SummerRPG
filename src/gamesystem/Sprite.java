package gamesystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
	private BufferedImage[] images;
	private double xOffset, yOffset;
	private double width, height;
	
	public void paint(Graphics g) {}
	
	public Sprite(BufferedImage[] imgs) {
		images= imgs;
	}
	
	public Sprite(BufferedImage img) {
		images= new BufferedImage[] {img};
	}
	
	// getters
	public BufferedImage[] getImages() {return images;}
}
