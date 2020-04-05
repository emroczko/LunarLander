package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

public class  Level extends JPanel{
    private ImageIcon backgroundImage;
    private Timer timer;
    private Lander lander;
    private boolean inGame = true;

    public Level(){
        initializeVariables();
        initializeLaytout();
    }
    private void initializeVariables(){
        this.lander = new Lander();
        this.backgroundImage = ImageFactory.createimiage(Image.background);
        this.timer = new Timer(10, new GameLoop(this) );
        this.timer.start();

    }

    private void initializeLayout(){
        setPreferredsize(new Dimensiosn(PropertiesLoad.xSize, PropertiesLoad.ySize));
    }
    private void drawPlayer(Graphics g{
        g.drawImage(lander.getImage(), lander.getX(), lander.getY(), this);

    }

    @Override
    protected void paintComponent(Graphics g)[
		super.paintComponent(g);
		g.drawImage(backgroundImage.getimage(),0, 0, null)

    doDrawing(g);
}

    private void doDrawing(graphics g){
        if(inGame){
            drawPlayer(g);
        }else{
            if(timer.isRunning()){
                timer.stop();
            }
            TOOLKIT.GETDEFAULTTOOLKIT().SYNC();

        }

        public void doOneLoop(){
            update();
            repaint();
        }
        private void update(){

        }

    }

