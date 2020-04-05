package company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Lander extends Sprite {
    public Lander() {
        initialize();
    }

    private void initialize() {
        ImageIcon imageicon = imageFactory.createimage(Image.Lunar);
        setImage(imageIcon.getImage());
        int start_x = Constans.Boarswidt / 2 - Cnostans.Spaceshipwidth / 2;
        int start_y = constans.boardhegiht;

        setX(start_x);
        setY(start_y);


    }
    @Override
    public void move() {
    }
}
