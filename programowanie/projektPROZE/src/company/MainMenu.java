package company;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {


    public MainMenu() {
        initializeLayout();
    }


    private void initializeLayout() {
        setTitle("Lunar Lander");
        setIconImage(ImageFactory.createImage(Image.Lander).getImage());
        add(new Menu());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

}

