package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WonGame extends JPanel{

    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;

    public WonGame(int xSize, int ySize){
        this.removeAll();
        repaint();
        revalidate();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));
        Fonts font = new Fonts();
        Color aqua = new Color (51, 134, 175);
        Color citron = new Color (223, 234, 24);


        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton startButton = new JButton("Play again!");
        JButton backButton = new JButton("Return to Main Menu");
        JLabel lost =new JLabel("YOU WON!!!");

        startButton.addActionListener(continueButtonListener());
        backButton.addActionListener(returnToMainMenuButtonListener());


        buttonCustomizer(startButton, citron, 32);
        buttonCustomizer(backButton, citron, 32);

        labelCustomizer(lost, aqua);


        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(lost, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(startButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
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
     * Odpowiada za kolor, czcionkę i wygląd przycisków w oknie przed grą
     */
    private void buttonCustomizer(JButton button, Color color, int fontSize){

        button.setFont(Fonts.getFont(fontSize));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(color);

    }

    /**
     * Odpowiada za kolor, czcionkę i wygląd napisów w oknie przed grą
     */
    private void labelCustomizer(JLabel label, Color color){

        label.setBackground(Color.black);
        label.setFont(Fonts.getFont(40));
        label.setForeground(color);

    }


    /**
     * Odpowiada za przypisanie akcji przyciskowi START
     */
    private ActionListener continueButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add(new Name(getWidth(),getHeight()),buttonsClickedBehaviour());
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
                add(new Menu(),buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za wyczyszczenie ekranu i umieszczenie nowego okna po naciśnięciu któregoś z przycisków w oknie Name
     */
    private GridBagConstraints buttonsClickedBehaviour(){
        removeAll();
        repaint();
        revalidate();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }
}
