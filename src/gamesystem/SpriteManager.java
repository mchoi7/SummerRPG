package gamesystem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class SpriteManager {
    public static SpriteManager res = new SpriteManager();
    
    private final Map<String, Sprite> spriteMap;
    
    private SpriteManager() {
        spriteMap = new HashMap<>();
        addSprite("strawberry");
    }
    
    private void addSprite(String spriteIndex) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(/*filepath???*/));
            spriteMap.put(spriteIndex, new Sprite(image));
        } catch(IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }
    
    public Sprite getSprite(String spriteIndex) {
        return spriteMap.get(spriteIndex);
    }
}
