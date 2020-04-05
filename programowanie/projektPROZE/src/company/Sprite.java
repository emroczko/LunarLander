package company;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

public abstract class Sprite {
    private Image image;
    private boolean dead;
    protected int x;
    protected int y;
    protected int dx;



    protected Rectangle2D rect;

    public abstract void move();

    public Sprite(){
        this.dead = false;
    }

    public void die(){
        this.dead = true;

    }

    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(){
        return this.image;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setRect(Rectangle2D r){
        this.rect =r;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    public Rectangle2D getRect() {
        return rect;
    }

    public void setdead(boolean dead){
        this.dead = dead;
    }
    public boolean isDead(){
        return this.dead;
    }

}
