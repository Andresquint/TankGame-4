package tankgame;

import java.awt.*;

public class CollisionDetector {

    Player playerOne, playerTwo;

    public CollisionDetector(Player player1, Player player2){
        this.playerOne = player1;
        this.playerTwo = player2;
    }

    public void playerVsbullet(Player player1, Player player2){
        Tank tank1 = player1.getTank();
        Tank tank2 = player2.getTank();

        Rectangle tankOneBox = new Rectangle(player1.getTank().getX(), player1.getTank().getY(), player1.getTank().getImg().getWidth(), player1.getTank().getImg().getHeight());
        Rectangle tankTwoBox = new Rectangle(player2.getTank().getX(), player2.getTank().getY(), player2.getTank().getImg().getWidth(), player2.getTank().getImg().getHeight());
        Rectangle bulletBox;
        for (Bullet b : tank1.bulletList){
            bulletBox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            if(tankTwoBox.intersects(bulletBox)){
                b.setExists(false);
                player2.setHealth(10);
            }
        }
        for (Bullet b : tank2.bulletList){
            bulletBox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            if(tankOneBox.intersects(bulletBox)){
                b.setExists(false);
                player1.setHealth(10);
            }
        }
    }

    public void bulletVswall(Player player1, Player player2){
        Rectangle bulletbox;
        Rectangle wallbox;

        for (Bullet b : player1.getTank().bulletList){
            bulletbox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            for (GameObject obj : GameWorld.getWorldList()){
                wallbox = new Rectangle(obj.getX(), obj.getY(), obj.getImg().getWidth(), obj.getImg().getHeight());
                if (bulletbox.intersects(wallbox)){
                    b.setExists(false);
                        if (((Wall) obj).breakable()){
                            obj.setExists(false);
                    }
                }
            }
        }

        for (Bullet b : player2.getTank().bulletList){
            bulletbox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            for (GameObject obj : GameWorld.getWorldList()){
                wallbox = new Rectangle(obj.getX(), obj.getY(), obj.getImg().getWidth(), obj.getImg().getHeight());
                if (bulletbox.intersects(wallbox)){
                    b.setExists(false);
                    if (((Wall) obj).breakable()){
                        obj.setExists(false);
                    }
                }
            }
        }
    }

}
