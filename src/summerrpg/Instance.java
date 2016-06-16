package summerrpg;

import java.awt.Graphics;
import static java.lang.Math.*;

public class Instance {

    /*====================================*/
    /*---------------Fields---------------*/

    private double x, dx, mdx, iddx, ddx, fddx;
    private double y, dy, mdy, iddy, ddy, fddy;
    private double width, height;
    private boolean active, visible, solid, hard;
    private double input[] = new double[8];
    private String spriteIndex;
    private int imageIndex, imageSpeed;
    private double imageXScale, imageYScale;

    /*====================================*/
    /*-------------Constructor------------*/

    public Instance(double x, double y, int type) {
        this.x = x;
        this.y = y;
        active = true;
        visible = true;
        InstanceManager.add(this, type);
        input[1] = 100;
        iddx = 0.01;
        iddy = 3;
        mdx = 3;
        mdy = 3;
    }

    /*====================================*/
    /*---------------Actions--------------*/

    public void update() {
        if(active) {
            registerInput();
            applyPhysics();
            move(dx, dy);
            calculateState();
        }
    }

    public void render(Graphics g) {
        if(visible) {
            Sprite sprite = SpriteManager.getSprite(spriteIndex);
            if(sprite != null) sprite.paint(g, x, y, imageIndex);
        }
    }

    public void destroy() {
        InstanceManager.remove(this);
    }

    /*====================================*/
    /*---------------Update---------------*/

    private void registerInput() {
        dx += (input[1] > 10 ? iddx : 0) - (input[3] > 10 ? iddx : 0); // Standard Input Commands Applies Input Acceleration Forces
        dy += (input[2] > 10 ? iddy : 0) - (input[0] > 10 ? iddy : 0);
    }

    private void applyPhysics() {
        dx += signum(dx) * fddx + ddx; // Apply Feedback Acceleration And Normal Acceleration To Velocity
        dy += signum(dy) * fddy + ddy;
        dx = abs(dx) > mdx ? signum(dx) * mdx : dx; // Limit Maximum Acceleration
        dy = abs(dy) > mdy ? signum(dy) * mdy : dy;
    }

    private void move(double dx, double dy) {
        if(solid && !hard) {
            x += dx;
            Instance block;
            block = InstanceManager.getFixedInstanceList().get((int) (x + y*LevelManager.getWidth()));
            if(block != null && this.isIntersecting(block))
                x = block.getX() - signum(block.getX() - x) * (width + block.getWidth()) / 2;
            for(Instance instance : InstanceManager.getFluidInstanceList())
                if(this.isIntersecting(instance)) {
                    double distance = instance.getX() - x;
                    dx -= 2 * iddx * (signum(distance) - 2 * distance / (width + instance.getWidth()));
                }
            y += dy;
            block = InstanceManager.getFixedInstanceList().get((int) (x + y*LevelManager.getWidth()));
            if(block != null && this.isIntersecting(block))
                y = block.getY() - signum(block.getY() - y) * (height + block.getHeight()) / 2;
            for(Instance instance : InstanceManager.getFluidInstanceList())
                if(this.isIntersecting(instance)) {
                    double distance = instance.getY() - y;
                    dy -= 2 * iddy * (signum(distance) - 2 * distance / (height + instance.getHeight()));
                }
        } else {
            x += dx;
            y += dy;
        }
    }

    private boolean isIntersecting(Instance instance) {
        return 2 * (instance.getX() - x) < width + instance.getWidth() && 2 * (instance.getY() - y) < height + instance.getHeight();
    }

    private void calculateState() {
        String spriteIndex = this.spriteIndex;
        calculateSprite();
        if(spriteIndex != null && this.spriteIndex != null)
            imageIndex = spriteIndex.equals(this.spriteIndex) ? imageIndex + imageSpeed : 0;
    }

    protected void calculateSprite() {
        //TEMPORARY
        spriteIndex = "sample";
    }

    /*====================================*/
    /*--------------Accessor--------------*/

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isHard() {
        return hard;
    }

    /*====================================*/
    /*---------------Mutator--------------*/

    public void setInput(double input[]) {
        this.input = input;
    }
}
