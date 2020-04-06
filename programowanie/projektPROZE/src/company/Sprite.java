package company;

import java.awt.Image;
import java.awt.geom.Rectangle2D;


/**
 * Klasa abstrakcyjna odpowiedzialna za rysowanie gracza
 */

public abstract class Sprite {
    /** Zmienna przechowująca obrazek*/
    private Image image;
    /** Zmienna określająca stan statku*/
    private boolean dead;
    /** Zmienna określająca położenie Landera w poziomie*/
    protected int x;
    /** Zmienna określająca położenie Landera w pionie*/
    protected int y;
    //protected int dx;



    protected Rectangle2D rect;

    public abstract void move();


    public Sprite(){
        this.dead = false;
    }
    /** metoda która "uśmierca" statek*/
    public void die(){
        this.dead = true;

    }
    /** metoda typu setter do ustanawiania obrazka statku*/
    public void setImage(Image image){
        this.image = image;
    }
    /** metoda typu getter do pobierania obrazka statku*/
    public Image getImage(){
        return this.image;
    }
    /** metoda typu setter do ustanawiania położenia statku w poziomie*/
    public void setX(int x){
        this.x = x;
    }
    /** metoda typu setter do ustanawiania położenia statku w pionie*/
    public void setY(int y){
        this.y = y;
    }
    /** metoda typu setter do ustanawiania obiektu typu Rectangle2D*/
    public void setRect(Rectangle2D r){
        this.rect =r;
    }
    /** metoda typu getter do pobierania położenia statku w poziomie*/
    public int getX(){
        return this.x;
    }
    /** metoda typu getter do pobierania położenia statku w pionie*/
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
