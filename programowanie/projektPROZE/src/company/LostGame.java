package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LostGame extends JPanel {

    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;
    private float points;

    Color aqua = new Color (51, 134, 175);
    Color citron = new Color (223, 234, 24);

    JButton startButton = new JButton("Play again");
    JButton backButton = new JButton("Return to main menu");
    JLabel lost = new JLabel("THE MISSION HAS FAILED");
    JLabel pointsLabel = new JLabel();

    LabelCustomizer customLabelAqua = new LabelCustomizer(aqua, 40);
    LabelCustomizer customLabelWhite = new LabelCustomizer(aqua, 40);
    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);

    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    public LostGame(int xSize, int ySize, float earnedPoints){
        this.removeAll();
        repaint();
        revalidate();
        points = earnedPoints;
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));

        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        startButton.addActionListener(playAgainButtonListener());
        backButton.addActionListener(returnToMainMenuButtonListener());

        pointsLabel.setText("Your score is: "+ earnedPoints);
        customButton.customizer(startButton);
        customButton.customizer(backButton);
        customLabelAqua.customizer(lost);
        customLabelWhite.customizer(pointsLabel);


        gbc.gridwidth = 3;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(15, 15, 15, 15);
            this.add(lost, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.add(pointsLabel, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            this.add(startButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            this.add(backButton, gbc);

            gbc.weightx = 1.0;
            gbc.weighty = 1.0;//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



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
        private ActionListener playAgainButtonListener() {
            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cleanWindow();
                    add(new Level(getWidth(),getHeight(),1, PropertiesLoad.numberOfLives, 0),newWindow.buttonsClickedBehaviour());
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
        newWindow.layoutMakerLostGame(this);
    }
}

