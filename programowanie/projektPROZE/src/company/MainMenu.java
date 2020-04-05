import company.Image;

import javax.swing.JFrame;

public class MainMenu extends JFrame {
    public MainMenu() {
        initializeLayout();
    }


    private void initializeLayout() {
        setTitle(Constans.TITLE);
        setIconImage(ImageFactory.createimage(Image.Lander).getImage());


        add(new Level());

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }
}
