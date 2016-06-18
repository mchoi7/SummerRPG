package summerrpg;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class SpriteManager {
    private static SpriteManager res = new SpriteManager();

    /*====================================*/
    /*---------------Fields---------------*/

    private final Map<String, Sprite> spriteMap;

    /*====================================*/
    /*-------------Constructor------------*/

    private SpriteManager() {
        spriteMap = new HashMap<>();
        addSprite("sample", 1);
    }
    
    private void addSprite(String spriteIndex, int imageNumber) {
        BufferedImage imageSheet;
        try {
            imageSheet = ImageIO.read(new File("src/resources/" + spriteIndex + ".png"));
        } catch(IOException e) {
            System.out.println("Image error: " + e.getMessage());
            return;
        }

        BufferedImage[] images = new BufferedImage[imageNumber];
        int width = imageSheet.getWidth() / imageNumber, height = imageSheet.getHeight();
        for(int imageIndex = 0; imageIndex < imageNumber; imageIndex++)
            images[imageIndex] = imageSheet.getSubimage(imageIndex * width, 0, width, height);

        spriteMap.put(spriteIndex, new Sprite(images));
    }

    /*====================================*/
    /*--------------Accessor--------------*/

    static Sprite getSprite(String spriteIndex) {
        return res.spriteMap.get(spriteIndex);
    }
}
