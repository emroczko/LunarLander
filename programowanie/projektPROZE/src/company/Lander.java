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
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP){
            //dx =
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
    }
}
