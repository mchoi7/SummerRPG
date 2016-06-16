package summerrpg;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class InstanceManager {
    public static final int FLUID = 0, FIXED = 1, TRIVIAL = 2, INTERFACE = 3;
    private static InstanceManager res = new InstanceManager();

    /*====================================*/
    /*---------------Fields---------------*/

    private final List<Instance> fixedInstanceList;
    private final List<Instance> fluidInstanceList;
    private final List<Instance> trivialInstanceList;
    private final List<Instance> interfaceInstanceList;

    /*====================================*/
    /*-------------Constructor------------*/

    private InstanceManager() {
        fluidInstanceList = new ArrayList<>();
        fixedInstanceList = new ArrayList<>();
        trivialInstanceList = new ArrayList<>();
        interfaceInstanceList = new ArrayList<>();
    }
    
    public static void add(Instance instance, int type) {
        switch (type) {
            case FLUID:
                res.fluidInstanceList.add(instance);
                break;
            case FIXED:
                res.fixedInstanceList.set((int) (instance.getX() + instance.getY()*LevelManager.getWidth()), instance);
                break;
            case TRIVIAL:
                res.trivialInstanceList.add(instance);
                break;
            case INTERFACE:
                res.interfaceInstanceList.add(instance);
                break;
        }
    }
    
    public static boolean remove(Instance instance) {
        return res.fixedInstanceList.remove(instance) || res.fluidInstanceList.remove(instance) || res.trivialInstanceList.remove(instance) || res.interfaceInstanceList.remove(instance);
    }

    public static void clear() {
        res.fluidInstanceList.clear();
        res.fixedInstanceList.clear();;
        res.trivialInstanceList.clear();;
        res.interfaceInstanceList.clear();
    }
    
    public static void update() {
        for(Instance instance : res.fluidInstanceList)
            instance.update();
        for(Instance instance : res.trivialInstanceList)
            instance.update();
        for(Instance instance : res.interfaceInstanceList)
            instance.update();
    }

    public static List<Instance> getFixedInstanceList() {
        return res.fixedInstanceList;
    }

    public static List<Instance> getFluidInstanceList() {
        return res.fluidInstanceList;
    }

    public static void render(Graphics g) {
        for(Instance instance : res.fixedInstanceList)
            instance.render(g);
        for(Instance instance : res.fluidInstanceList)
            instance.render(g);
        for(Instance instance : res.trivialInstanceList)
            instance.render(g);
        for(Instance instance : res.interfaceInstanceList)
            instance.render(g);
    }
}
