package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

public class  Level extends JPanel
{
    private ImageIcon backgroundImage;
    private Timer timer;
    private Lander lander;
    private boolean inGame = true;

    public Level() {
        initializeVariables();
        initializeLayout();
    }

    private void initializeVariables() {
        this.lander = new Lander();
<<<<<<< HEAD
        this.backgroundImage = ImageFactory.createImage(Image.Earth1);
        this.timer = new Timer(10, new GameLoop(this));
=======
        this.backgroundImage = ImageFactory.createImage(Image.Background);
        this.timer = new Timer(10, new GameLoop(this) );
>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235
        this.timer.start();

    }

<<<<<<< HEAD
    private void initializeLayout() {
=======
    private void initializeLayout(){
>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235
        setPreferredSize(new Dimension(PropertiesLoad.xSize, PropertiesLoad.ySize));
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(lander.getImage(), lander.getX(), lander.getY(), this);

    }

    @Override
<<<<<<< HEAD
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, null);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawPlayer(g);}
        else {
            if (timer.isRunning()) {
                timer.stop();
            }
            Toolkit.getDefaultToolkit().sync();
=======
    protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(),0, 0, null);
        doDrawing(g);
    }

    private void doDrawing(Graphics g){
        if(inGame){
            drawPlayer(g);
        }else{
            if(timer.isRunning()){
                timer.stop();
            }
            toolkit.getDefaultToolkit().sync();

>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235
        }

        public void doOneLoop () {
            this.update();
            this.repaint();
        }

        private void update () {
            System.out.println("Update");
        }

    }
}


