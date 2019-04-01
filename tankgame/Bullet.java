package tankgame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends MovingObject {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private boolean exists = true;

    private final int R = 4;
    private BufferedImage img;

    public Bullet(int x, int y, int angle, int vx, int vy, int speed, int rotationalSpeed, BufferedImage img){
        super(x, y, angle, vx, vy, speed, rotationalSpeed, img);
    }

    private void checkBorder() {
        if (this.getX() < 30) {
            this.setX(30);
            this.exists = false;
        }
        if (this.getX() >= TRE.WORLD_WIDTH - 88) {
            this.setX(TRE.WORLD_WIDTH - 88);
            this.exists = false;
        }
        if (this.getY() < 30) {
            this.setY(30);
            this.exists = false;
        }
        if (this.getX() >= TRE.WORLD_HEIGHT - 80) {
            this.setX(TRE.WORLD_HEIGHT - 80);
            this.exists = false;
        }
    }

    public boolean exists(){
        return this.exists;
    }

    public void update(){
        moveForwards();
        checkBorder();
    }



}
