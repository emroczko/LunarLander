package company;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Lander extends Sprite {
    public Lander() {
        initialize();
    }

    private void initialize() {
        ImageIcon imageIcon = ImageFactory.createImage(Image.Lander);
        setImage(imageIcon.getImage());
        int start_x = PropertiesLoad.xSize / 2 - PropertiesLoad.LanderWidth / 2;
        int start_y = PropertiesLoad.ySize-450;

        setX(start_x);
        setY(start_y);


    }
    @Override
    public void move() {
    }
}
