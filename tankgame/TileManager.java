package tankgame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {

    BufferedImage background;

    public TileManager(BufferedImage background){
        this.background = background;
    }

    public void drawLayout(Graphics2D buffer){
        buffer.clearRect(0,0, TRE.WORLD_WIDTH, TRE.WORLD_HEIGHT);
        for (int y = 0; y < TRE.WORLD_HEIGHT; y+=TRE.WORLD_HEIGHT/5 ){
            for (int x = 0; x < TRE.WORLD_WIDTH; x+=TRE.WORLD_WIDTH/5){
                buffer.drawImage(background, x, y, null);
            }
        }
    }



}
