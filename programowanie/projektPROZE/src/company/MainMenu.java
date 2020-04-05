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

        //add(new Level());


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
