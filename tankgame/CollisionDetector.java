package tankgame;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CollisionDetector {

    Player player1, player2;

    public CollisionDetector(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public void playerVsbullet(){
        Rectangle tankOneBox = new Rectangle(player1.getTank().getX(), player1.getTank().getY(), player1.getTank().getImg().getWidth(), player1.getTank().getImg().getHeight());
        Rectangle tankTwoBox = new Rectangle(player2.getTank().getX(), player2.getTank().getY(), player2.getTank().getImg().getWidth(), player2.getTank().getImg().getHeight());
        Rectangle bulletBox;
        for (Bullet b : player1.getTank().bulletList){
            bulletBox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            if(tankTwoBox.intersects(bulletBox)){
                b.setExists(false);
                player2.setHealth(25);
            }
        }
        for (Bullet b : player2.getTank().bulletList){
            bulletBox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            if(tankOneBox.intersects(bulletBox)){
                b.setExists(false);
                player1.setHealth(25);
            }
        }
    }

    public void bulletVswall(){
        Rectangle bulletbox;
        Rectangle wallbox;

        for (Bullet b : player1.getTank().bulletList){
            bulletbox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            for (GameObject obj : GameWorld.getWorldList()) {
                if (obj instanceof Wall) {
                    wallbox = new Rectangle(obj.getX(), obj.getY(), obj.getImg().getWidth(), obj.getImg().getHeight());
                    if (bulletbox.intersects(wallbox)) {
                        b.setExists(false);
                        if (((Wall) obj).breakable()) {
                            obj.setExists(false);
                        }
                    }
                }
            }
        }

        for (Bullet b : player2.getTank().bulletList){
            bulletbox = new Rectangle(b.getX(), b.getY(), b.getImg().getWidth(), b.getImg().getHeight());
            for (GameObject obj : GameWorld.getWorldList()) {
                if (obj instanceof Wall) {
                    wallbox = new Rectangle(obj.getX(), obj.getY(), obj.getImg().getWidth(), obj.getImg().getHeight());
                    if (bulletbox.intersects(wallbox)) {
                        b.setExists(false);
                        if (((Wall) obj).breakable()) {
                            obj.setExists(false);
                        }
                    }
                }
            }
        }
    }

    public void playerVsobject(){

        Rectangle tankOneBox = new Rectangle(player1.getTank().getX(), player1.getTank().getY(), player1.getTank().getImg().getWidth(), player1.getTank().getImg().getHeight());
        Rectangle tankTwoBox = new Rectangle(player2.getTank().getX(), player2.getTank().getY(), player2.getTank().getImg().getWidth(), player2.getTank().getImg().getHeight());
        Rectangle objBox;
        Rectangle2D intersection;
        if (tankOneBox.intersects(tankTwoBox)){
            intersection = tankOneBox.createIntersection(tankTwoBox);
            if (intersection.getMaxX() >= tankOneBox.getMaxX()){
                player1.getTank().setX(player1.getTank().getX() - 1);
                player2.getTank().setX(player2.getTank().getX() + 1);
            }
            if(intersection.getMaxX() >= tankTwoBox.getMaxX()){
                player1.getTank().setX(player1.getTank().getX() + 1);
                player2.getTank().setX(player2.getTank().getX() - 1);
            }
            if(intersection.getMaxY() >= tankOneBox.getMaxY()){
                player1.getTank().setY(player1.getTank().getY() - 1);
                player2.getTank().setY(player2.getTank().getY() + 1);
            }
            if(intersection.getMaxY() >= tankTwoBox.getMaxY()){
                player1.getTank().setY(player1.getTank().getY() + 1);
                player2.getTank().setY(player2.getTank().getY() - 1);
            }
            if(player1.getTank().getUpPressed()){
                player1.getTank().toggleUpPressed();
            }
            if(player1.getTank().getDownPressed()){
                player1.getTank().toggleDownPressed();
            }
            if(player2.getTank().getUpPressed()){
                player2.getTank().toggleUpPressed();
            }
            if(player2.getTank().getDownPressed()){
                player2.getTank().toggleDownPressed();
            }
        }

        for (GameObject obj : GameWorld.getWorldList()){
            objBox = new Rectangle(obj.getX(), obj.getY(), obj.getImg().getWidth(), obj.getImg().getHeight());
            if(obj instanceof Wall){
                if (tankOneBox.intersects(objBox)) {
                    intersection = tankOneBox.createIntersection(objBox);
                    if (intersection.getMaxX() >= tankOneBox.getMaxX()){
                        player1.getTank().setX(player1.getTank().getX() - 2);
                    }
                    if(intersection.getMaxX() >= objBox.getMaxX()){
                        player1.getTank().setX(player1.getTank().getX() + 2);
                    }
                    if(intersection.getMaxY() >= tankOneBox.getMaxY()){
                        player1.getTank().setY(player1.getTank().getY() - 2);
                    }
                    if(intersection.getMaxY() >= objBox.getMaxY()){
                        player1.getTank().setY(player1.getTank().getY() + 2);
                    }

                }
                if (tankTwoBox.intersects(objBox)){
                    intersection = tankTwoBox.createIntersection(objBox);
                    if (intersection.getMaxX() >= tankTwoBox.getMaxX()){
                        player2.getTank().setX(player2.getTank().getX() - 2);
                    }
                    if(intersection.getMaxX() >= objBox.getMaxX()){
                        player2.getTank().setX(player2.getTank().getX() + 2);
                    }
                    if(intersection.getMaxY() >= tankTwoBox.getMaxY()){
                        player2.getTank().setY(player2.getTank().getY() - 2);
                    }
                    if(intersection.getMaxY() >= objBox.getMaxY()){
                        player2.getTank().setY(player2.getTank().getY() + 2);
                    }
                }
            }
            if (obj instanceof Powerup){
                if (tankOneBox.intersects(objBox)){
                    obj.setExists(false);
                    player1.setHealth(-30);
                }
                if (tankTwoBox.intersects(objBox)){
                    obj.setExists(false);
                    player2.setHealth(-30);
                }
            }
        }

    }

}
