package company;

import javax.swing.*;
import java.awt.geom.Rectangle2D;

public class Asteroid extends Sprite {

    private Level level;
    private int velx;
    private int vely;
    private int direction;

    public Asteroid(int start_x, int start_y, int velx, int vely, int direction, Level level) {
        initialize(start_x, start_y, direction, velx, vely, level);
    }


    private void initialize(int start_x, int start_y, int velx, int vely ,int dicercion ,Level level){
        ImageIcon imageIcon = ImageFactory.createImage(Image.Asteroid);
        setImage(imageIcon.getImage());
        setX(start_x);
        setY(start_y);
        this.velx = velx;
        this.vely = vely;
        this.level = level;
        this.direction = direction;
        Rectangle2D rect = new Rectangle2D.Float(this.getX(), this.getY(), 20, 20);
        setRect(rect);
    }

    private void updateRect() {
        Rectangle2D rect = new Rectangle2D.Float((int)(x*((float)(level.getWidth())/PropertiesLoad.xSize)), (int)(y*((float)level.getHeight()/PropertiesLoad.ySize)), (int)(20*((float)level.getWidth()/PropertiesLoad.xSize)), (int)(20*((float)level.getHeight()/PropertiesLoad.ySize)));
        setRect(rect);
    }

    public void update() {
        move();
        updateRect();
    }

    public void setLevel(Level lev)
    {
        this.level = lev;
    }

    @Override
    public void move(){
        if (this.direction == 0){
            x-=velx;
        }
        else{
            x+=velx;
        }
        y+=vely;
    }
}
