package summerrpg;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class InstanceManager {
    private static InstanceManager res = new InstanceManager();

    /*====================================*/
    /*---------------Fields---------------*/

    private final List<Instance> instanceList;
    private Instance[][] instanceArray;

    /*====================================*/
    /*-------------Constructor------------*/

    private InstanceManager() {
        instanceList = new ArrayList<>();
        instanceArray = new Instance[64][64];
    }

    public static void add(Instance instance) {
        res.instanceList.add(instance);
    }

    public static void add(Instance instance, double x, double y) {
        res.instanceArray[(int) (x / 16)][(int) (y / 16)] = instance;
    }

    public static boolean remove(Instance instance) {
        return res.instanceList.remove(instance);
    }

    public static boolean remove(Instance instance, double x, double y) {
        int xIndex = (int) (x / 16);
        int yIndex = (int) (y / 16);
        if(res.instanceArray[xIndex][yIndex] != null) {
            res.instanceArray[xIndex][yIndex] = null;
            return true;
        }
        return false;
    }

    public static void clear() {
        res.instanceList.clear();
    }
    
    public static void update() {
        for(Instance instance : res.instanceList)
            instance.update();
    }

    public static List<Instance> getInstanceList() {
        return res.instanceList;
    }

    public static Instance getInstance(double x, double y) {
        try {
            return res.instanceArray[(int) (x / 16)][(int) (y / 16)];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void render(Graphics g) {
        for(int y = 0; y < res.instanceArray.length; y++)
            for(int x = 0; x < res.instanceArray[0].length; x++)
                if(res.instanceArray[x][y] != null)
                    res.instanceArray[x][y].render(g);
        for(Instance instance : res.instanceList)
            instance.render(g);
    }
}
