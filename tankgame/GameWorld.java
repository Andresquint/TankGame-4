package tankgame;

import java.awt.image.*;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class GameWorld {

    private BufferedImage background;
    private BufferedImage tankImg;
    private BufferedImage bulletImg;
    private TileManager tileManager;
    private Tank player1;
    private Tank player2;

    public GameWorld(){
        try {
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            background = read(new File("Background.bmp"));
            tankImg  = read(new File("tank.png"));
            bulletImg = read(new File("bullet.png"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        player1 = new Tank(tankImg, TRE.WORLD_WIDTH/4 - 25, TRE.WORLD_HEIGHT/4 - 25, 0);
        player2 = new Tank(tankImg, 3*TRE.WORLD_WIDTH/4 - 25, 3*TRE.WORLD_HEIGHT/4 - 25, 180);

        tileManager = new TileManager(background);
    }



    public void drawWorld(Graphics2D buffer){
        tileManager.drawLayout(buffer);
    }
    public Tank getPlayer(int player){
        if (player==1){
            return this.player1;
        }
        else{
            return this.player2;
        }
    }

    public BufferedImage getTankImg(){
        return this.tankImg;
    }

}
