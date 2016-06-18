package summerrpg;

import java.awt.Graphics;
import static java.lang.Math.*;

public class Instance {

    /*====================================*/
    /*---------------Fields---------------*/

    private double x, dx, mdx, iddx, ddx, fddx;
    private double y, dy, mdy, iddy, ddy, fddy;
    private double width, height;
    private boolean active, visible, solid, fixed;
    private boolean key[] = new boolean[256];
    private String spriteIndex;
    private int imageIndex, imageSpeed;
    private double imageXScale, imageYScale;

    /*====================================*/
    /*-------------Constructor------------*/

    public Instance(double x, double y) {
        this.x = x;
        this.y = y;

        InstanceManager.add(this);
    }

    public void setBasic() {
        active = true;
        visible = true;
        solid = true;

        imageXScale = 1;
        imageYScale = 1;

        width = 16;
        height = 16;

        fddx = -0.1;
        fddy = -0.1;

        mdx = 3;
        mdy = 3;

        spriteIndex = "sample";
    }

    public void setBlock() {
        setBasic();

        fixed = true;
        InstanceManager.remove(this);
        InstanceManager.add(this, x, y);
    }

    public void setPlayer() {
        setBasic();

        iddx = 0.3;
        iddy = 0.3;

        fddx = -0.1;
        fddy = -0.1;

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
            if(sprite != null) sprite.paint(g, x, y, imageXScale, imageYScale, imageIndex);
        }
    }

    public void destroy() {
        InstanceManager.remove(this);
    }

    /*====================================*/
    /*---------------Update---------------*/

    private void registerInput() {
        dx += (key['D'] ? iddx : 0) - (key['A'] ? iddx : 0); // Standard Input Commands Applies Input Acceleration Forces
        dy += (key['S'] ? iddy : 0) - (key['W'] ? iddy : 0);
    }

    private void applyPhysics() {
        dx = (abs(dx) > -fddx ? dx + signum(dx) * fddx : 0) + ddx; // Apply Feedback Acceleration And Normal Acceleration To Velocity
        dy = (abs(dy) > -fddy ? dy + signum(dy) * fddy : 0) + ddy;

        dx = abs(dx) > mdx ? signum(dx) * mdx : dx; // Limit Maximum Acceleration
        dy = abs(dy) > mdy ? signum(dy) * mdy : dy;
    }

    private void move(double dx, double dy) {
        if(solid && !fixed) {
            Instance block;

            x += dx;

            for(double j = -height; j <= height; j += height)
                for(double i = -width; i <= width; i += width) {
                    block = InstanceManager.getInstance(x + i, y + j);
                    if(block != null && this.isIntersecting(block))
                        x = block.getX() - signum(dx) * (width + block.getWidth()) / 2;
                }

            y += dy;

            for(double j = -height; j <= height; j += height)
                for(double i = -width; i <= width; i += width) {
                    block = InstanceManager.getInstance(x + i, y + j);
                    if (block != null && this.isIntersecting(block))
                        y = block.getY() - signum(dy) * (height + block.getHeight()) / 2;
                }

            for(Instance instance : InstanceManager.getInstanceList())
                if(this != instance && this.isIntersecting(instance)) {
                    double angle = atan2(instance.getY() - y, instance.getX() - x);
                    this.dx -= 0.5 * iddx * cos(angle);
                    this.dy -= 0.5 * iddy * sin(angle);
                }

        } else {
            x += dx;
            y += dy;
        }
    }

    private boolean isIntersecting(Instance instance) {
        return 2 * abs(instance.getX() - x) < (width + instance.getWidth()) && 2 * abs(instance.getY() - y) < (height + instance.getHeight());
    }

    private void calculateState() {
        String spriteIndex = this.spriteIndex;
        calculateSprite();
        if(spriteIndex != null && this.spriteIndex != null)
            imageIndex = spriteIndex.equals(this.spriteIndex) ? imageIndex + imageSpeed : 0;
    }

    protected void calculateSprite() {
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

    public boolean isFixed() {
        return fixed;
    }

    /*====================================*/
    /*---------------Mutator--------------*/

    public void setKey(boolean[] key) {
        this.key = key;
    }
}
