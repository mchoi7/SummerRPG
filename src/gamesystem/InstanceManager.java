package gamesystem;

import java.util.List;
import java.util.ArrayList;

public class InstanceManager {
    public static InstanceManager res = new InstanceManager();
    
    private final List<Instance> fluidInstanceList;
    private final List<Instance> fixedInstanceList;
    
    private InstanceManager() {
        fluidInstanceList = new ArrayList<>();
        fixedInstanceList = new ArrayList<>();
    }
    
    public void addInstance(Instance instance, boolean fixed) {
        if(fixed) {
            fixedInstanceList.set((int) instance.getX() + (int) instance.getY()*GameFrame.WIDTH, instance);
        } else {
            fluidInstanceList.add(instance);
        }
    }
    
    public boolean removeInstance(Instance instance) {
        if(fluidInstanceList.remove(instance) || fixedInstanceList.remove(instance)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void clear() {
        fluidInstanceList.clear();
        fixedInstanceList.clear();
    }
    
    public void update() {
        
    }
    
    public void render() {
        
    }
}
