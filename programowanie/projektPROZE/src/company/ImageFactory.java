package company;

import javax.swing.*;

public class ImageFactory {
    public static ImageIcon createImage(Image image) {
        ImageIcon imageIcon = null;

        switch (image) {
<<<<<<< HEAD
            case UFO:
                imageIcon = new ImageIcon(Constans.Ufo_image_url);
                break;
            case Lander
                imageIcon = new ImageIcon(Constans.Ufo_image_url);
=======
            case Lander:
                imageIcon = new ImageIcon("Images/Lander.png");
                break;
            case Earth1:
                imageIcon = new ImageIcon("Images/EarthMoon.png");
>>>>>>> c00e5df2e7e32d64399ba47308bff595882f8e7f
                break;
            case Earth2:
                imageIcon = new ImageIcon("Images/EarthMoon2.png");
                break;
            case Mars1:
                imageIcon = new ImageIcon("Images/MarsMoon.png");
                break;
            case Mars2:
                imageIcon = new ImageIcon("Images/MarsMoon2.png");
                break;
            case Jupiter1:
                imageIcon = new ImageIcon("Images/JupiterMoon.png");
                break;
            case Jupiter2:
                imageIcon = new ImageIcon("Images/JupiterMoon2.png");
                break;
            case Saturn1:
                imageIcon = new ImageIcon("Images/SaturnMoon.png");
                break;
            case Saturn2:
                imageIcon = new ImageIcon("Images/SaturnMoon2.png");
            default:
                break;
        }
        return imageIcon;
    }
}