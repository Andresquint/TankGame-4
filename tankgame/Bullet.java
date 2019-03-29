package tankgame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    public boolean exists = true;

    private final int R = 4;
    private BufferedImage img;

    Bullet(int x, int y, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
    }

    public void update(){
        moveForwards();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    private void checkBorder() {
        if (x < 30) {
            this.exists = false;
        }
        if (x >= 1600 - 88) {
            this.exists = false;
        }
        if (y < 40) {
            this.exists = false;
        }
        if (y >= 1200 - 80) {
            this.exists = false;
        }
    }

    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }


}
