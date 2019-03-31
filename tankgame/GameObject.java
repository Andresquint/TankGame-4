package tankgame;

import java.awt.image.BufferedImage;

public class GameObject {

    private int x;
    private int y;
    private BufferedImage img;

    public GameObject(int x, int y, BufferedImage img){
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){

    }
}
