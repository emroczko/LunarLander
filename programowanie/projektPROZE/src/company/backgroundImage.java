package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class backgroundImage extends JPanel{
    Image image;
    public backgroundImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}
