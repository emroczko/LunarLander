package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WonLevel extends JPanel {

    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;
    private int wonLevelNumber;
    private int lives;
    private float points;
    /** Kolor niebieski używany w oknie*/
    Color aqua = new Color (51, 134, 175);
    /** Kolor żółty używany w oknie*/
    Color citron = new Color (223, 234, 24);
    LabelCustomizer customLabel = new LabelCustomizer(aqua, 40);
    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);
    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    JButton startButton = new JButton("Continue");
    JButton backButton = new JButton("Return to Main Menu");
    JLabel wonLabel =new JLabel("GOOD JOB!");


    public WonLevel(int xSize, int ySize, int wonLevel, int leftLives, float earnedPoints){
        this.removeAll();
        repaint();
        revalidate();
        wonLevelNumber = wonLevel;
        lives = leftLives;
        points = earnedPoints;
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));

        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        startButton.addActionListener(continueButtonListener());
        backButton.addActionListener(returnToMainMenuButtonListener());

        customButton.customizer(startButton);
        customButton.customizer(backButton);
        customLabel.customizer(wonLabel);

        this.add(wonLabel, customGBC.gbcCustomize(0,1 ,0,0,3,"none"));
        this.add(startButton, customGBC.gbcCustomize(0,3 ,0, 0, 3, "none"));
        this.add(backButton, customGBC.gbcCustomize(0,4 ,0, 0, 3, "none"));

        customGBC.gbcCustomize(0,0,1,1,0,"none");

    }
    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
    }
    /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi START
     */
    private ActionListener continueButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Level(getWidth(),getHeight(), wonLevelNumber+1, lives, points),newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi BACK
     */
    private ActionListener returnToMainMenuButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Menu(),newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    private void cleanWindow(){
        newWindow.layoutMakerWonLevel(this);
    }
}

