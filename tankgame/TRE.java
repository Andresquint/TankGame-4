/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Objects;

import static javax.imageio.ImageIO.read;
import java.io.File;
/**
 *
 * @author anthony-pc
 */
public class TRE extends JPanel  {


    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 450;
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 1600;

    private GameWorld gameWorld;
    private Player player1;
    private Player player2;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;
    private BufferedImage background;
    private Camera cam1;
    private Camera cam2;

    private CollisionDetector CD;




    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        try {

            while (true) {
                trex.player1.getTank().update();
                trex.player2.getTank().update();
                trex.CD.playerVsbullet(trex.player1, trex.player2);
                trex.CD.bulletVswall(trex.player1, trex.player2);
                trex.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }

    }


    private void init() {
        this.jf = new JFrame("Tank Rotation");
        this.world = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, t2img = null, bulletimg = null;
        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            t1img = read(new File("tank1.png"));
            t2img = read(new File("tank1.png"));
            bulletimg = read(new File("bullet.png"));
            //background = read(new File("Background.bmp"));



        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        gameWorld = new GameWorld();

        player1 = new Player(gameWorld.getPlayer(1));
        player2 = new Player(gameWorld.getPlayer(2));

        CD = new CollisionDetector(player1, player2);

        //t1 = new Tank(WORLD_WIDTH/4 - 25, WORLD_HEIGHT/4 - 25, 0, 0, 0, 3, 2, t1img, bulletimg);
        //t2 = new Tank(3*WORLD_WIDTH/4 - 25, 3*WORLD_HEIGHT/4 - 25, 180, 0, 0, 3, 2, t2img, bulletimg);


        TankControl tc1 = new TankControl(player1.getTank(), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(player2.getTank(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(TRE.SCREEN_WIDTH+1, TRE.SCREEN_HEIGHT + 269);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

        this.setBackground(Color.black);


    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);
        gameWorld.drawWorld(buffer);

        cam1 = new Camera(this.player1.getTank());
        cam2 = new Camera(this.player2.getTank());

        g2.drawImage((world.getSubimage(cam1.getX(), cam1.getY(), SCREEN_WIDTH/2, SCREEN_HEIGHT)),0,0,null);
        g2.drawImage((world.getSubimage(cam2.getX(), cam2.getY(),  SCREEN_WIDTH/2, SCREEN_HEIGHT)), SCREEN_WIDTH/2+1, 0, null);
        g2.drawImage((world.getSubimage(0, 0, WORLD_WIDTH, WORLD_HEIGHT)), SCREEN_WIDTH/2-SCREEN_WIDTH/8, SCREEN_HEIGHT, SCREEN_WIDTH/4, 240, null);
        g2.drawImage(gameWorld.getTankImg(), SCREEN_WIDTH/32, 33*SCREEN_HEIGHT/32, null);
        g2.drawImage(gameWorld.getTankImg(), 2*SCREEN_WIDTH/3 - 10, 33*SCREEN_HEIGHT/32, null);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g2.drawString(("Health: " + this.player1.getHealth()), SCREEN_WIDTH/30, 34*SCREEN_HEIGHT/32);
        g2.drawString(("Health: " + this.player2.getHealth()), 2*SCREEN_WIDTH/3 + 10, 34*SCREEN_HEIGHT/32);



    }


}
