package company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Lander {
    //środek obrazka
    private int pos_x;
    private int pos_y;
    //Prędkości
    private float vx;
    private float vy;

    private ImageIcon lander_image;
    private int lifes_number;

    private Rectangle2D rect;

    Lander(int screen_Width, int screen_Hight)
    {
          lander_image = new ImageIcon("Images/Lander.png");
          pos_x = screen_Width/2;
          pos_y = screen_Hight -50;
          rect = new Rectangle2D.Float(40, 40, pos_x - 20, pos_y - 20);

    }
    public void drawLander(Graphics g, int resizex, int resizey)
    {
        g.drawImage(lander_image.getImage(), pos_x-20, pos_y-20, resizex, resizey, null);
    }
    public Rectangle2D get_rect()
    {
        return this.rect;
    }
}
