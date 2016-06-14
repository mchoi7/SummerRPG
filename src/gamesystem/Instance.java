package gamesystem;

public class Instance {
	private double x, dx, ddx;	// physics
	private double y, dy, ddy;
	
	private Sprite spriteIndex;	// animation
	private int imageIndex;
	private double imageSpeed;
	private int depth;
	private double imageXScale, imageYScale;
	
	private boolean solid, active, visible;
		
	public Instance() {
		x= 0; dx= 0; ddx= 0;
		y= 0; dy= 0; ddy= 0;
		spriteIndex= null; imageIndex= 0; imageSpeed= 0; depth= 0;
		solid= false; active= false; visible= false;
	}
	
	// getters
	public double getX() {return x;}
	public double getY() {return y;}
	public double getDx() {return dx;}
	public double getDy() {return dy;}
	public double getDdx() {return ddx;}
	public double getDdy() {return ddy;}
	public Sprite getSpriteIndex() {return spriteIndex;}
	public int getImageIndex() {return imageIndex;}
	public double getImageSpeed() {return imageSpeed;}
	public int getDepth() {return depth;}
	public double getImageXScale() {return imageXScale;}
	public double getImageYScale() {return imageYScale;}
	public boolean getSolid() {return solid;}
	public boolean getActive() {return active;}
	public boolean getVisible() {return visible;}

	// setters
	public void setX(double value) {x= value;}
	public void setY(double value) {y= value;}
	public void setDx(double value) {dx= value;}
	public void setDy(double value) {dy= value;}
	public void setDdx(double value) {ddx= value;}
	public void setDdy(double value) {ddy= value;}
	public void setSpriteIndex(Sprite value) {spriteIndex= value;}
	public void setImageIndex(int value) {imageIndex= value;}
	public void setImageSpeed(double value) {imageSpeed= value;}
	public void setDepth(int value) {depth= value;}
	public void setImageXScale(double value) {imageXScale= value;}
	public void setImageYScale(double value) {imageYScale= value;}
	public void setSolid(boolean value) {solid= value;}
	public void setActive(boolean value) {active= value;}
	public void setVisible(boolean value) {visible= value;}
	
	public void collision() {}
	
	public void update() {
		// physics
		dx += ddx; dy+= ddy;
		x += dx; y += ddy;
	}
	
	public void paint() {}
}