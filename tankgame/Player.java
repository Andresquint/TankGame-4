package tankgame;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Player {

    private int lives;
    private int health;
    private int armor;
    private int score;


    private BufferedImage tankImg;

    private Tank tank;

    public Player(Tank tank){
        this.lives = 3;
        this.health = 100;
        this.armor = 0;
        this.score = 0;

        try {
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            tankImg = read(new File("tank.png"));
            //background = read(new File("Background.bmp"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        this.tank = tank;
    }

    public Tank getTank(){
        return this.tank;
    }
    public void setHealth(int damage){
        this.health = this.health - damage;
        System.out.println("Current Health: " + this.health);
    }
    public int getHealth(){
        return this.health;
    }

}
