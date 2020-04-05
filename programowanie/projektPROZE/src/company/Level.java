package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

public class Level extends JPanel{
    private ImageIcon backgroundImage;
    private Timer timer;
    private Lander lander;
    private boolean inGame = true;
    int a,b;


    public Level(int xSize, int ySize) {

        this.removeAll();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(xSize,ySize));


        try {
            PropertiesLoad.loadMapsConfigs(1);
        }
        catch(Exception e){e.printStackTrace();}

        revalidate();
        repaint();
        initializeVariables();

    }

    private void initializeVariables(){
        this.lander = new Lander();
        this.backgroundImage = ImageFactory.createImage(Image.Earth1);
        this.timer = new Timer(10, new GameLoop(this));
        this.timer.start();

    }



    private void drawPlayer(Graphics g){
        g.drawImage(lander.getImage(), (int)(lander.getX()*((float)(this.getWidth())/700)),
                (int)(lander.getY()*((float)this.getHeight()/500)), (int)(this.getWidth()/17.5),
                (int)(this.getHeight()/12.5), this);
        g.setColor(Color.red);
        g.drawRect(lander.getX(), lander.getY(), 40, 40);
    }
    private void drawGround(Graphics g){
        Polygon moon = new Polygon(PropertiesLoad.xPoints, PropertiesLoad.yPoints, PropertiesLoad.xPoints.length);
        Polygon landing = new Polygon(PropertiesLoad.xLanding, PropertiesLoad.yLanding, PropertiesLoad.xLanding.length);
        g.setColor(Color.green);
        g.drawPolygon(moon);
        g.setColor(Color.blue);
        g.drawPolygon(landing);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawPlayer(g);
            drawGround(g);
        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }
    public void doOneLoop(){
        this.update();
        this.repaint();
    }

    private void update(){

    }


}



