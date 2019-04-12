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

import static javax.imageio.ImageIO.read;
import java.io.File;
/**
 *
 * @author anthony-pc
 */
public class TRE<frameCount> extends JPanel  {


    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 450;
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 1600;

    private GameWorld gameWorld;
    public Player player1;
    public Player player2;

    private BufferedImage forwardHeart;
    private BufferedImage reverseHeart;
    private BufferedImage reverseTank;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;
    private BufferedImage background;
    private Camera cam1;
    private Camera cam2;
    public boolean GameOver = false;

    private CollisionDetector CD;

    public static int framecount = 0;




    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        try {

            while (!(trex.GameOver)) {
                trex.player1.getTank().update();
                trex.player2.getTank().update();
                trex.CD.playerVsbullet();
                trex.CD.bulletVswall();
                trex.CD.playerVsobject();
                trex.repaint();
                framecount++;
                Thread.sleep(1000 / 144);
                if(trex.player1.getLives() == 0 || trex.player2.getLives() == 0){
                    trex.GameOver = true;
                }
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
          forwardHeart = read(new File("forwardHeart.png"));
          reverseHeart = read(new File("reverseHeart.png"));
          reverseTank = read(new File("reverseTank.png"));



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


        this.jf.setSize(TRE.SCREEN_WIDTH+1, TRE.SCREEN_HEIGHT + 275);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

        this.setBackground(Color.black);
        this.setForeground(Color.GREEN);

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
        g2.drawImage((world.getSubimage(0, 0, WORLD_WIDTH, WORLD_HEIGHT)), SCREEN_WIDTH/2-SCREEN_WIDTH/8, SCREEN_HEIGHT + 1, SCREEN_WIDTH/4, 245, null);
        g2.drawImage(gameWorld.getTankImg(), SCREEN_WIDTH/32 - 15, 33*SCREEN_HEIGHT/32, null);
        g2.drawImage(reverseTank, SCREEN_WIDTH - 75, 33*SCREEN_HEIGHT/32, null);


        for (int i = 0; i < player1.getLives(); i++){
        g2.drawImage(forwardHeart, SCREEN_WIDTH/32 - 17, 33*SCREEN_HEIGHT/32 + 55*(1 + i), null);
        }
        for (int i = 0; i < player2.getLives(); i++){
            g2.drawImage(reverseHeart, SCREEN_WIDTH - 68, 33*SCREEN_HEIGHT/32 + 55*(1 + i), null);
        }

        g2.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        g2.setColor(Color.BLUE);
        g2.drawString(("Shields: "), SCREEN_WIDTH/30 + 47, 34*SCREEN_HEIGHT/32 + 75);
        g2.drawOval(SCREEN_WIDTH/30 + 180, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH/30 + 230, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH/30 + 280, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH/30 + 330, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH - 410, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH - 360, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH - 310, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        g2.drawOval(SCREEN_WIDTH - 260, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);
        for (int i = 0; i < player1.getShields(); i++){

            g2.fillOval(SCREEN_WIDTH/30 + 180 + 50*i, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);

        }
        for (int i = 0; i < player2.getShields(); i++){

            g2.fillOval(SCREEN_WIDTH - 410 + 50 * i, 34*SCREEN_HEIGHT/32 + 75 - 30, 40, 40);

        }
        g2.drawString((":Shields "), 2*SCREEN_WIDTH/3 + 192, 34*SCREEN_HEIGHT/32 + 75);

        g2.setColor(Color.WHITE);

        g2.drawString(("Range:"), SCREEN_WIDTH/30 +50, 34*SCREEN_HEIGHT/32 + 125 );
        if (player1.getRange() == 50) {
            g2.setColor(Color.RED);
        } else if (player1.getRange() > 50 && player1.getRange() < Player.MAX_RANGE){
            g2.setColor(Color.YELLOW);
        }else{
            g2.setColor(Color.GREEN);
        }
        g2.drawLine(SCREEN_WIDTH/30 + 178, 34*SCREEN_HEIGHT/32 + 115, SCREEN_WIDTH/30 + 178 + player1.getRange()*2,34*SCREEN_HEIGHT/32 + 115 );

        g2.setColor(Color.WHITE);

        g2.drawString((":Range"), 2*SCREEN_WIDTH/3 + 192, 34*SCREEN_HEIGHT/32 + 125 );
        if (player2.getRange() == 50) {
            g2.setColor(Color.RED);
        } else if (player2.getRange() > 50 && player2.getRange() < Player.MAX_RANGE){
            g2.setColor(Color.YELLOW);
        }else{
            g2.setColor(Color.GREEN);
        }
        g2.drawLine(2*SCREEN_WIDTH/3 + 180, 34*SCREEN_HEIGHT/32 + 115, 2*SCREEN_WIDTH/3 + 180 - player2.getRange()*2,34*SCREEN_HEIGHT/32 + 115 );

        g2.setColor(Color.GREEN);

        if (player1.getLives() == 0 || player2.getLives() == 0){
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 350));
            g2.drawString("GAME", 75, 350);
            g2.drawString("OVER", 90, 620);
            return;
        } else {
            g2.drawRect(SCREEN_WIDTH / 30 + 50, 33 * SCREEN_HEIGHT / 32, Player.MAX_HEALTH, 50);
            g2.fillRect(SCREEN_WIDTH / 30 + 50, 33 * SCREEN_HEIGHT / 32, player1.getHealth(), 50);
            g2.drawRect(SCREEN_WIDTH - 415, 33 * SCREEN_HEIGHT / 32, Player.MAX_HEALTH, 50);
            g2.fillRect(SCREEN_WIDTH - 415 + Player.MAX_HEALTH - player2.getHealth(), 33 * SCREEN_HEIGHT / 32, player2.getHealth(), 50);
        }


    }


}
