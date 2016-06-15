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
        addSprite("test");
    }
    
    private void addSprite(String spriteIndex) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(spriteIndex + ".png"));
        } catch(IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        spriteMap.put(spriteIndex, new Sprite(/*images[]*/));
    }
    
    public Sprite getSprite(String spriteIndex) {
        return spriteMap.get(spriteIndex);
    }
}
