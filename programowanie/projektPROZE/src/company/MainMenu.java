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

<<<<<<< HEAD

        add(new Menu());
=======
        add(new Level());
>>>>>>> 3a8a8cf5e8e538f5e09926c1f20d6393725020b9

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void startLevel(){
        add(new Level());
    }




}
