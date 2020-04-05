import javax.swing.JFrame;

public class MainMenu extends JFrame {
    public MainMenu() {
        initializeLayout();
    }


    private void initializeLayout() {
        setTitle(Constans.TITLE);
        seticonImage(ImageFactory.createimage(image.spaceship).getImage());


        add(new GamePanel());

        pack();
        setDefaultCoseOperative(Exitonclose);
        setLocationRelativetoNull
        setResizable(true);
        setVisible(true);
    }
}
