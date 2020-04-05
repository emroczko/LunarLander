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
<<<<<<< HEAD
        int start_x = Constans.Boarswidt / 2 - Cnostans.Spaceshipwidth / 2;
        int start_y = constans.boardhegiht;
=======
        int start_x = PropertiesLoad.xSize/2-PropertiesLoad.LunarWidth/2;
        int start_y = PropertiesLoad.ySize;
>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235

        setX(start_x);
        setY(start_y);


    }
    @Override
    public void move() {
    }
}
