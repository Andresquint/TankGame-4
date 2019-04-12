package tankgame;

import java.awt.image.BufferedImage;

public class Powerup extends GameObject{

    private int type;

    public Powerup(BufferedImage img, int x, int y, int type)   {
        super(x, y, 0, img);
        this.type = type;
    }

    @Override
    public void update() {

    }
}
