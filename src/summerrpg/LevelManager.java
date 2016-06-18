package summerrpg;

import java.io.*;

class LevelManager {
    private static LevelManager ourInstance = new LevelManager();
    private static final String PLAYER = "P", BLOCK = "X";

    /*====================================*/
    /*---------------Fields---------------*/



    /*====================================*/
    /*-------------Constructor------------*/

    private LevelManager() {
    }

    /*====================================*/
    /*--------------Accessor--------------*/

    static void loadLevel(String levelIndex) {
        InstanceManager.clear();

        try {
            FileReader fileReader = new FileReader("src/resources/" + levelIndex + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String levelInfo = bufferedReader.readLine();
            bufferedReader.readLine();

            String line;
            int x = 0, y = 0;
            while((line = bufferedReader.readLine()) != null) {
                for(String symbol : line.substring(1).split("\\.")) {
                    switch(symbol) {
                        case PLAYER:
                            Instance player = new Instance(x, y);
                            player.setPlayer();
                            InstanceManager.setPlayer(player);
                            break;
                        case BLOCK:
                            Instance block = new Instance(x, y);
                            block.setHard();
                            break;
                    }
                    x += 16;
                }
                x = 0;
                y += 16;
            }

            // Always close files.
            bufferedReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("Open File Error: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Read File Error: " + e.getMessage());
        }
    }

    static int getWidth() {
        return 0;
    }

    static int getHeight() {
        return 0;
    }
}
