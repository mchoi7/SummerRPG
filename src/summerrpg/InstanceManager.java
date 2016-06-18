package summerrpg;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

class InstanceManager {
    private static InstanceManager res = new InstanceManager();

    /*====================================*/
    /*---------------Fields---------------*/

    private Instance player;
    private final List<Instance> instanceList;
    private Instance[][] instanceArray;

    /*====================================*/
    /*-------------Constructor------------*/

    private InstanceManager() {
        instanceList = new ArrayList<>();
        instanceArray = new Instance[64][64];
    }

    static Instance getPlayer() {
        return res.player;
    }

    static void setPlayer(Instance player) {
        res.player = player;
    }

    static void add(Instance instance) {
        res.instanceList.add(instance);
    }

    static void add(Instance instance, double x, double y) {
        res.instanceArray[(int) (x / 16)][(int) (y / 16)] = instance;
    }

    static boolean remove(Instance instance) {
        return res.instanceList.remove(instance);
    }

    static boolean remove(Instance instance, double x, double y) {
        int xIndex = (int) (x / 16);
        int yIndex = (int) (y / 16);
        if(res.instanceArray[xIndex][yIndex] != null) {
            res.instanceArray[xIndex][yIndex] = null;
            return true;
        }
        return false;
    }

    static void clear() {
        res.instanceList.clear();
    }
    
    static void update() {
        for(Instance instance : res.instanceList)
            instance.update();
    }

    static List<Instance> getInstanceList() {
        return res.instanceList;
    }

    static Instance getInstance(double x, double y) {
        try {
            return res.instanceArray[(int) (x / 16)][(int) (y / 16)];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    static void render(Graphics g) {
        for(int y = 0; y < res.instanceArray.length; y++)
            for(int x = 0; x < res.instanceArray[0].length; x++)
                if(res.instanceArray[x][y] != null)
                    res.instanceArray[x][y].render(g);
        for(Instance instance : res.instanceList)
            instance.render(g);

        int textX = 10, textY = 20;
        String information = "x: " + res.player.getX() + "\ny: " + res.player.getY() + "\ndx: " + res.player.getDx() + "\ndy: " + res.player.getDy();
        for (String line : information.split("\n"))
            g.drawString(line, textX, textY += g.getFontMetrics().getHeight());
    }
}
