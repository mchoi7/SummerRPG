package summerrpg;

public class LevelManager {
    private static LevelManager ourInstance = new LevelManager();

    /*====================================*/
    /*---------------Fields---------------*/



    /*====================================*/
    /*-------------Constructor------------*/

    private LevelManager() {
    }

    /*====================================*/
    /*--------------Accessor--------------*/

    public static void loadLevel(String levelIndex) {
        InstanceManager.clear();
    }

    public static int getWidth() {
        return 0;
    }

    public static int getHeight() {
        return 0;
    }
}
