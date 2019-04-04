package tankgame;

import java.awt.image.*;

public class Wall extends GameObject {

    boolean breakable;

    public Wall(BufferedImage img, int x, int y, int type){

        super(x, y, 0, img);
        if (type == 1){
            this.breakable = false;
        }else{
            this.breakable = true;
        }

    }

    @Override
    public void update() {

    }
}
