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


    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 600;
    public static final int WORLD_WIDTH = 2560;
    public static final int WORLD_HEIGHT = 2560;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;
    private BufferedImage grassimg;
    private Camera cam1;
    private Camera cam2;



    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        try {

            while (true) {
                trex.t1.update();
                trex.t2.update();
                trex.repaint();
                System.out.println(trex.t1);
                System.out.println(trex.t2);
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
            grassimg = read(new File("grasstile.png"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t1 = new Tank(WORLD_WIDTH/4 - 25, WORLD_HEIGHT/4 - 25, 0, 0, 0, t1img, bulletimg);
        t2 = new Tank(3*WORLD_WIDTH/4 - 25, 3*WORLD_HEIGHT/4 - 25, 0, 0, 180, t2img, bulletimg);


        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);


    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);
        buffer.clearRect(0,0, WORLD_WIDTH, WORLD_HEIGHT);
        for (int y = 0; y < WORLD_HEIGHT; y+=WORLD_HEIGHT/10 ){
            for (int x = 0; x < WORLD_WIDTH; x+=WORLD_WIDTH/10){
                buffer.drawImage(grassimg, x, y, null);
            }
        }
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);
        for (int i = 0; i < this.t1.bulletList.size(); i++){
            if (t1.bulletList.get(i).exists) {
                t1.bulletList.get(i).drawImage(buffer);
            } else {
                t1.bulletList.remove(i);
            }
        }
        for (int i = 0; i < this.t2.bulletList.size(); i++){
            if (t2.bulletList.get(i).exists) {
                t2.bulletList.get(i).drawImage(buffer);
            } else {
                t2.bulletList.remove(i);
            }
        }
        cam1 = new Camera(t1);
        cam2 = new Camera(t2);

        g2.drawImage((world.getSubimage(cam1.getX(), cam1.getY(), SCREEN_WIDTH/2-1, SCREEN_HEIGHT)),0,0,null);
        g2.drawImage((world.getSubimage(cam2.getX(), cam2.getY(),  SCREEN_WIDTH/2, SCREEN_HEIGHT)), SCREEN_WIDTH/2, 0, null);

    }


}
