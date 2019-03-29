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


    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;
    private BufferedImage grassimg;


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
        this.world = new BufferedImage(2560, 2560, BufferedImage.TYPE_INT_RGB);
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
        t1 = new Tank(SCREEN_WIDTH/4 - 25, SCREEN_HEIGHT/2 - 25, 0, 0, 0, t1img, bulletimg);
        t2 = new Tank(800, 300, 0, 0, 0, t2img, bulletimg);


        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

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
        buffer.clearRect(0,0, world.getWidth(), world.getHeight());
        for (int y = 0; y < 2560; y+=256 ){
            for (int x = 0; x < 2560; x+=256){
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
        int camt1X = t1.getX() - SCREEN_WIDTH/4;
        int camt1Y = t1.getY() - SCREEN_HEIGHT/2;
        int camt2X = t2.getX() - SCREEN_WIDTH/4;
        int camt2Y = t2.getY() - SCREEN_HEIGHT/2;
        if (camt1X > world.getWidth() - SCREEN_WIDTH/2){
            camt1X = world.getWidth() - SCREEN_WIDTH/2;
        }else if (camt1X < 0){
            camt1X = 0;
        }
        if (camt1Y > world.getHeight() - SCREEN_HEIGHT ){
            camt1Y = world.getHeight() - SCREEN_HEIGHT;
        }else if(camt1Y < 0){
            camt1Y = 0;
        }

        if (camt2X > world.getWidth() - SCREEN_WIDTH/2){
            camt2X = world.getWidth() - SCREEN_WIDTH/2;
        }else if (camt2X < 0){
            camt2X = 0;
        }
        if (camt2Y > world.getHeight() - SCREEN_HEIGHT ){
            camt2Y = world.getHeight() - SCREEN_HEIGHT;
        }else if(camt2Y < 0){
            camt2Y = 0;
        }
        //g2.drawImage(world, 0, 0, null);
        g2.drawImage((world.getSubimage(camt1X, camt1Y, SCREEN_WIDTH/2-1, SCREEN_HEIGHT)),0,0,null);
        g2.drawImage((world.getSubimage(camt2X, camt2Y,  SCREEN_WIDTH/2, SCREEN_HEIGHT)), SCREEN_WIDTH/2, 0, null);

    }


}
