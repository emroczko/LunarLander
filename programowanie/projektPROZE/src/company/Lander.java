package company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Klasa odpowiedzialna za utworzenie statku
 */

public class Lander extends Sprite {

    public Lander() {
        initialize();
    }

    /**
     * Funkcja inicjująca zmienne odziedziczone od klasy Sprite
     */
    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.Lander);
        setImage(imageIcon.getImage());
        int start_x = PropertiesLoad.xSize / 2 - PropertiesLoad.LanderWidth / 2;
        int start_y = PropertiesLoad.ySize-450;
        Rectangle2D rect = new Rectangle2D.Float(this.getX(), this.getY(), 40, 40);
        setX(start_x);
        setY(start_y);
        setRect(rect);
    }
    @Override
    public void move() {
        //System.out.println(this.velx + "" + this.vely);
        this.x += velx;
        this.y += vely;
        //System.out.println(this.x + " " + this.y);
    }
    public void acceleration(float accX, float accY){
        this.velx += accX;
        this.vely += accY;
    }
    public void update(){
        move();
        acceleration(0, PropertiesLoad.mapGravity);
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        System.out.println("key event");

        if(key == KeyEvent.VK_UP){
            System.out.println(PropertiesLoad.enginePowerVx);
            vely -= PropertiesLoad.enginePowerVy;
            vely += PropertiesLoad.mapGravity;
        }
        if(key == KeyEvent.VK_LEFT){
            velx -= PropertiesLoad.enginePowerVx;
        }
        if(key == KeyEvent.VK_RIGHT){
            velx += PropertiesLoad.enginePowerVx;
        }
    }
    public void keyReleased(KeyEvent e){
    }
}
