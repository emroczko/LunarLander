<<<<<<< HEAD
import company.Image;
=======
package company;


>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235

import javax.swing.JFrame;

public class MainMenu extends JFrame {
    public MainMenu() {
        initializeLayout();
    }


    private void initializeLayout() {
<<<<<<< HEAD
        setTitle(Constans.TITLE);
        setIconImage(ImageFactory.createimage(Image.Lander).getImage());
=======
        setTitle(PropertiesLoad.Title);
        setIconImage(ImageFactory.createImage(Image.Lander).getImage());
>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235


        add(new Level());

        pack();
<<<<<<< HEAD
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
=======
>>>>>>> ce543b38e6215e2596473ae99ad50d117e8e3235
        setResizable(true);
        setVisible(true);
    }
}
