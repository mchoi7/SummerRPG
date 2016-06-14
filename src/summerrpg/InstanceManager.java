package summerrpg;

import java.util.List;
import java.util.ArrayList;

public class InstanceManager {
    public static InstanceManager res = new InstanceManager();
    
    private List<Instance> fluidInstance;
    private List<Instance> fixedInstance;
    
    private InstanceManager() {
        fluidInstance = new ArrayList<>();
        fixedInstance = new ArrayList<>();
    }
    
    public void addInstance(Instance instance, boolean fixed) {
        if(fixed) {
            fixedInstance.set(instance.getX() + GameFrame.WIDTH*instance.getY(), instance);
        } else {
            fluidInstance.add(instance);
        }
    }
    
    public boolean removeInstance(Instance instance) {
        if(fluidInstance.remove(instance) || fixedInstance.remove()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void clear() {
        fluidInstance.clear();
        fixedInstance.clear();
    }
    
    public void update() {
        
    }
    
    public void render() {
        
    }
}
