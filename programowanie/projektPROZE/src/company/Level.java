package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

public class Level extends Canvas
{
    private Lander player;
    private BufferStrategy bufstrat;
    public Level(int levelNumber){
        player = new Lander(this.getWidth(), this.getHeight());this.repaint();
    }
    public final void addNotify()
    {
        super.addNotify();
        createBufferStrategy(2);
        bufstrat = getBufferStrategy();
    }
    public void paint(Graphics g){
        super.paint(g);
        player.drawLander(g, this.getWidth(), this.getHeight());
    }
}
