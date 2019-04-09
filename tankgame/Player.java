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
        this.health = 275;
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
        if ((this.health - damage) > 0) {
            this.health = this.health - damage;
            System.out.println("Current Health: " + this.health);
        }
        else {
            this.health = 275;
            this.loseLife();
        }
    }
    public int getHealth(){
        return this.health;
    }

    public int getLives(){
        return this.lives;
    }

    public void loseLife(){
        if (this.lives != 1){
            this.lives--;
        }else{
            this.lives = 0;
            this.getTank().setExists(false);
        }
    }

}
