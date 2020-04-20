package company;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Klasa odpowiedzialna za utworzenie statku
 */

public class Lander extends Sprite {

    private Level level;

    public Lander(Level level) {
        this.level = level;
        initialize();
    }

    /**
     * Funkcja inicjująca zmienne odziedziczone od klasy Sprite
     */
    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.Lander);
        setImage(imageIcon.getImage());
        int start_x = PropertiesLoad.xSize / 2 - PropertiesLoad.LanderWidth / 2;
        int start_y = PropertiesLoad.ySize - 450;
        Rectangle2D rect = new Rectangle2D.Float(this.getX(), this.getY(), 40, 40);
        setX(start_x);
        setY(start_y);
        setRect(rect);
    }

    @Override
    public void move() {

        x += velx;
        y += vely;

        if(x<0){
            x = level.getWidth();
        }
        if(x>level.getWidth()){
            x = 0;
        }
    }
    private void updateRect() {
        Rectangle2D rect = new Rectangle2D.Float(this.getX(), this.getY(), 40, 40);
        setRect(rect);
    }
    public void acceleration(float accX, float accY) {
        this.velx += accX;
        this.vely += accY;

        level.labelUpdate("vx");
        level.labelUpdate("vy");

    }

    public void update() {
        move();
        updateRect();
        acceleration(0, PropertiesLoad.mapGravity);
    }

    public void moveUp() {
        vely -= PropertiesLoad.enginePowerVy;
        //vely += PropertiesLoad.mapGravity;
    }

    public void moveDown() {
        vely += PropertiesLoad.enginePowerVy;
        //vely += PropertiesLoad.mapGravity;
    }

    public void moveLeft() {
        velx -= PropertiesLoad.enginePowerVx;
    }

    public void moveRight() {
        velx += PropertiesLoad.enginePowerVx;
    }
}