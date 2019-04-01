package tankgame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class MovingObject extends GameObject {

    private int vx;
    private int vy;
    private final int speed;
    private final int rotationalSpeed;

    public MovingObject(int x, int y, int angle, int vx, int vy, int speed, int rotationalSpeed, BufferedImage img){
        super(x, y, angle, img);
        this.vx = vx;
        this.vy = vy;
        this.speed = speed;
        this.rotationalSpeed = rotationalSpeed;
    }

    protected void moveForwards() {
        vx = (int) Math.round(this.speed * Math.cos(Math.toRadians(this.getAngle())));
        vy = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.getAngle())));
        this.setX(this.getX() + this.vx);
        this.setY(this.getY() + this.vy);
        checkBorder();
    }

    protected void rotateLeft() {
        this.setAngle(this.getAngle() - this.rotationalSpeed);
    }

    protected void rotateRight() {
        this.setAngle(this.getAngle() + this.rotationalSpeed);
    }

    protected void moveBackwards() {
        this.vx = (int) Math.round(this.speed * Math.cos(Math.toRadians(this.getAngle())));
        this.vy = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.getAngle())));
        this.setX(this.getX() - this.vx);
        this.setY(this.getY() - this.vy);
        checkBorder();
    }

    private void checkBorder() {
        if (this.getX() < 30) {
            this.setX(30);
        }
        if (this.getX() >= TRE.WORLD_WIDTH - 88) {
            this.setX(TRE.WORLD_WIDTH - 88);
        }
        if (this.getY() < 30) {
            this.setY(30);
        }
        if (this.getY() >= TRE.WORLD_HEIGHT - 80) {
            this.setY(TRE.WORLD_HEIGHT - 80);
        }
    }

    abstract public void update();

}
