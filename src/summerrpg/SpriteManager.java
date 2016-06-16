package summerrpg;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpriteManager {
    private static SpriteManager res = new SpriteManager();

    private final Map<String, Sprite> spriteMap;
    
    private SpriteManager() {
        spriteMap = new HashMap<>();
        addSprite("sample", 2);
    }
    
    private void addSprite(String spriteIndex, int imageNumber) {
        BufferedImage imageSheet = null;
        try {
            imageSheet = ImageIO.read(new File("src/images/" + spriteIndex + ".png"));
        } catch(IOException e) {
            System.out.println("Image error: " + e.getMessage());
            return;
        }

        BufferedImage[] images = new BufferedImage[imageNumber];
        int width = imageSheet.getWidth() / imageNumber;
        int height = imageSheet.getHeight();
        for(int imageIndex = 0; imageIndex < imageNumber; imageIndex++)
            images[imageIndex] = imageSheet.getSubimage(imageIndex * width, 0, width, height);

        spriteMap.put(spriteIndex, new Sprite(images));
    }
    
    public static Sprite getSprite(String spriteIndex) {
        return res.spriteMap.get(spriteIndex);
    }
}
