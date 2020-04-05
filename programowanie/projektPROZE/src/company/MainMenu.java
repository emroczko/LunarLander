package company;



import javax.swing.JFrame;

public class MainMenu extends JFrame {
    public MainMenu() {
        initializeLayout();
    }


    private void initializeLayout() {
        setTitle(PropertiesLoad.Title);
        setIconImage(ImageFactory.createImage(Image.Lander).getImage());


        add(new Level());

        pack();
        setResizable(true);
        setVisible(true);
    }
}
