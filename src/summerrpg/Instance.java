package summerrpg;

import java.awt.Graphics;
import static java.lang.Math.*;

class Instance {

    /*====================================*/
    /*---------------Fields---------------*/

    protected enum State {
        Idle
    }
    private int stateIndex;

    private double x, y, dx, dy;
    private double[] mdx, ddx, fddx, mdy, ddy, fddy;
    private double[][] dds;

    private double width, height, depth, weight;
    private boolean active, visible, solid, fixed;
    private boolean key[];
    private String spriteIndex;
    private double imageIndex, imageSpeed;
    private double imageXScale, imageYScale;

    /*====================================*/
    /*-------------Constructor------------*/

    Instance(double x, double y) {
        this.x = x;
        this.y = y;

        int stateSize = State.values().length;

        key = new boolean[256];
        dds = new double[stateSize][4];
        mdx = new double[stateSize];
        ddx = new double[stateSize];
        fddx = new double[stateSize];

        mdy = new double[stateSize];
        ddy = new double[stateSize];
        fddy = new double[stateSize];

        InstanceManager.add(this);
    }

    private void setBasic() {
        active = true;
        visible = true;
        solid = true;

        spriteIndex = "timer";
        imageXScale = 1;
        imageYScale = 1;
        
        width = 16;
        height = 16;
        depth = 0;
        weight = 1;
        
        fddx[State.Idle.ordinal()] = -0.25;
        fddy[State.Idle.ordinal()] = -0.25;
    }

    void setHard() {
        setBasic();
        imageSpeed = random();

        fixed = true;
        InstanceManager.remove(this);
        InstanceManager.add(this, x, y);
    }

    void setSoft() {
        setBasic();

        mdx[State.Idle.ordinal()] = 3;
        mdy[State.Idle.ordinal()] = 3;
    }

    void setPlayer() {
        setSoft();

        weight = 1;
        imageSpeed = 0.5;
        dds[State.Idle.ordinal()][0] = .5;
        dds[State.Idle.ordinal()][1] = .5;
        dds[State.Idle.ordinal()][2] = .5;
        dds[State.Idle.ordinal()][3] = .5;
    }

    /*====================================*/
    /*---------------Actions--------------*/

    void update() {
        if(active) {
            
            // Apply Normal Acceleration
            dx += ddx[stateIndex];
            dy += ddy[stateIndex];

            // Apply Feedback Acceleration
            dx = (abs(dx) > -fddx[stateIndex] ? dx + signum(dx) * fddx[stateIndex] : 0);
            dy = (abs(dy) > -fddy[stateIndex] ? dy + signum(dy) * fddy[stateIndex] : 0);

            double iddx = (key['D'] ? dds[stateIndex][3] : 0) - (key['A'] ? dds[stateIndex][1] : 0);
            double iddy = (key['S'] ? dds[stateIndex][2] : 0) - (key['W'] ? dds[stateIndex][0]: 0);

            // Apply Input Acceleration
            dx = (signum(iddx) * (dx + iddx) <= mdx[stateIndex]) ? dx + iddx : (abs(dx) < mdx[stateIndex] ? signum(dx) * mdx[stateIndex] : dx);
            dy = (signum(iddy) * (dy + iddy) <= mdy[stateIndex]) ? dy + iddy : (abs(dy) < mdy[stateIndex] ? signum(dy) * mdy[stateIndex] : dy);
            
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
        
        stateIndex = State.Idle.ordinal();
        
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
