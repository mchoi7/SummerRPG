package summerrpg;

import java.awt.Graphics;
import static java.lang.Math.*;

class Instance {

    /*====================================*/
    /*---------------Fields---------------*/

    private double x, dx, mdx, ddx, fddx;
    private double y, dy, mdy, ddy, fddy;
    private double[] dds = new double[4];
    private double width, height, depth, weight;
    private boolean active, visible, solid, fixed;
    private boolean key[] = new boolean[256];
    private String spriteIndex;
    private int imageIndex, imageSpeed;
    private double imageXScale, imageYScale;

    /*====================================*/
    /*-------------Constructor------------*/

    Instance(double x, double y) {
        this.x = x;
        this.y = y;

        InstanceManager.add(this);
    }

    void setBasic() {
        active = true;
        visible = true;
        solid = true;

        spriteIndex = "sample";
        imageXScale = 1;
        imageYScale = 1;

        fddx = -0.25;
        fddy = -0.25;
        width = 16;
        height = 16;
        depth = 0;
        weight = 1;
    }

    void setHard() {
        setBasic();

        fixed = true;
        InstanceManager.remove(this);
        InstanceManager.add(this, x, y);
    }

    void setSoft() {
        setBasic();

        mdx = 3;
        mdy = 3;
    }

    void setPlayer() {
        setSoft();

        weight = 1;
        dds[0] = .5;
        dds[1] = .5;
        dds[2] = .5;
        dds[3] = .5;
    }

    /*====================================*/
    /*---------------Actions--------------*/

    void update() {
        if(active) {

            // Apply Normal Acceleration
            dx += ddx;
            dy += ddy;

            // Apply Feedback Acceleration
            dx = (abs(dx) > -fddx ? dx + signum(dx) * fddx : 0);
            dy = (abs(dy) > -fddy ? dy + signum(dy) * fddy : 0);

            double iddx = (key['D'] ? dds[3] : 0) - (key['A'] ? dds[1] : 0);
            double iddy = (key['S'] ? dds[2] : 0) - (key['W'] ? dds[0]: 0);

            // Apply Input Acceleration
            dx = (signum(iddx) * (dx + iddx) <= mdx) ? dx + iddx : (abs(dx) < mdx ? signum(dx) * mdx : dx);
            dy = (signum(iddy) * (dy + iddy) <= mdy) ? dy + iddy : (abs(dy) < mdy ? signum(dy) * mdy : dy);
            move(dx, dy);
            calculateState();
        }
    }

    void render(Graphics g) {
        if(visible) {
            Sprite sprite = SpriteManager.getSprite(spriteIndex);
            if(sprite != null) sprite.paint(g, x, y, imageXScale, imageYScale, imageIndex);
        }
    }

    void destroy() {
        InstanceManager.remove(this);
    }

    /*====================================*/
    /*---------------Update---------------*/

    private void move(double dx, double dy) {
        if(solid && !fixed) {
            Instance block;

            x += dx;
            for(double j = -height; j <= height; j += height)
                for(double i = -width; i <= width; i += width) {
                    block = InstanceManager.getInstance(x + i, y + j);
                    if(block != null && this.isIntersecting(block)) {
                        x = block.getX() - signum(dx) * (width + block.getWidth()) / 2;
                        this.dx = 0;
                    }
                }

            y += dy;
            for(double j = -height; j <= height; j += height)
                for(double i = -width; i <= width; i += width) {
                    block = InstanceManager.getInstance(x + i, y + j);
                    if (block != null && this.isIntersecting(block)) {
                        y = block.getY() - signum(dy) * (height + block.getHeight()) / 2;
                        this.dy = 0;
                    }
                }

            for(Instance instance : InstanceManager.getInstanceList())
                if(this != instance && this.isIntersecting(instance)) {
                    double angle = atan2(instance.getY() - y, instance.getX() - x);
                    this.dx -= instance.getWeight() * cos(angle) / (2 * weight);
                    this.dy -= instance.getWeight() * sin(angle) / (2 * weight);
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
        /* TODO: make subclasses overriding this method to determine sprite state */
    }

    /*====================================*/
    /*--------------Accessor--------------*/

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double getDx() {
        return dx;
    }

    double getDy() {
        return dy;
    }

    double getWeight() {
        return weight;
    }

    double getDepth() {
        return depth;
    }

    double getWidth() {
        return width;
    }

    double getHeight() {
        return height;
    }

    /*====================================*/
    /*---------------Mutator--------------*/

    void setKey(boolean[] key) {
        this.key = key;
    }
}
