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
        player = new Lander(this.getWidth(), this.getHeight());
    }
    public void render_objects(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        player.drawLander(g, this.getWidth(), this.getHeight());
    }
    public final void addNotify()
    {
        super.addNotify();
        createBufferStrategy(2);
        bufstrat = getBufferStrategy();
    }
    public class Animation_Timer extends TimerTask{
        public void run()
        {
            do {
                do {
                    Graphics graph = bufstrat.getDrawGraphics();
                    render_objects(graph);
                    graph.dispose();
                } while(bufstrat.contentsRestored());
                bufstrat.show();
            }while(bufstrat.contentsLost());
        }
    }
}
