package summerrpg;

import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class InstanceManager {
    private static InstanceManager res = new InstanceManager();
    
    private final List<Instance> fluidInstanceList;
    private final List<Instance> fixedInstanceList;
    
    private InstanceManager() {
        fluidInstanceList = new ArrayList<>();
        fixedInstanceList = new ArrayList<>();
    }
    
    public static void addInstance(Instance instance, boolean fixed) {
        if(fixed) {
            res.fixedInstanceList.set((int) instance.getX() + (int) instance.getY()*GameFrame.WIDTH, instance);
        } else {
            res.fluidInstanceList.add(instance);
        }
    }
    
    public static boolean removeInstance(Instance instance) {
        return res.fluidInstanceList.remove(instance) || res.fixedInstanceList.remove(instance);
    }
    
    public static void clear() {
        res.fluidInstanceList.clear();
        res.fixedInstanceList.clear();
    }
    
    public static void update() {
        for(Instance instance : res.fluidInstanceList) {
            instance.update();
        }
    }
    
    public static void paint(Graphics g) {

        // TESTING
        Sprite sprite = SpriteManager.getSprite("sample");
        if(sprite != null) sprite.paint(g, 100, 100, 0);
        // TESTING

        for(Instance instance : res.fluidInstanceList)
            instance.render();
        for(Instance instance : res.fixedInstanceList)
            instance.render();
    }
}
