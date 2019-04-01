package tankgame;



import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;
import java.io.File;

/**
 *
 * @author anthony-pc
 */
public class Tank extends MovingObject{

    private BufferedImage bulletImg;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    public ArrayList<Bullet> bulletList;


    Tank(int x, int y, int angle, int vx, int vy, int speed, int rotationalSpeed, BufferedImage img, BufferedImage bulletImg) {
       super(x, y, angle, vx, vy, speed, rotationalSpeed, img);
        this.bulletList = new ArrayList<Bullet>();
        this.bulletImg = bulletImg;
    }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {this.ShootPressed = true;}

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {this.ShootPressed = false;}


@Override
    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if(this.ShootPressed) {
            this.shoot();
            this.unToggleShootPressed();
        }
        for (Bullet b : this.bulletList){
                b.update();
            }

        }

    private void shoot(){
        Bullet pBullet = new Bullet(this.getX()+30, this.getY()+15, this.getAngle(), 0, 0, 6, 0, bulletImg);
        this.bulletList.add(pBullet);
        System.out.println("Fire!");

    }


}
