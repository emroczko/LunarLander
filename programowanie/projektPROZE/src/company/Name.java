package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Klasa odpowiedzialna za obsługę zdarzeń z graficznego interfejsu użytkownika, tutaj za pobranie nicku gracza i rozpoczęcie gry
 */

public class Name extends JPanel{
    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;

    public Name(int xSize, int ySize){
        this.removeAll();
        repaint();
        revalidate();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));

        Color aqua = new Color (51, 134, 175);
        Color citron = new Color (223, 234, 24);


        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton startButton = new JButton("Start!");
        JButton backButton = new JButton("Back");
        JLabel typeNick=new JLabel("Type your nick:");
        JTextField enterName = new JTextField("Your nick...");

        startButton.addActionListener(startButtonListener(enterName));
        backButton.addActionListener(backButtonListener());

        enterName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                enterName.setText("");
            }
        });

        buttonCustomizer(startButton, citron);
        buttonCustomizer(backButton, citron);

        labelCustomizer custom = new labelCustomizer(Color.lightGray, 36);
        custom.customizer(typeNick);

        textFieldCustomizer(enterName, aqua);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(typeNick, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;

        this.add(enterName, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(startButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
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
    private void buttonCustomizer(JButton button, Color color){

        button.setFont(Fonts.getFont(32));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(color);

    }
/*
    /**
     * Odpowiada za kolor, czcionkę i wygląd napisów w oknie przed grą

    private void labelCustomizer(JLabel label, Color color){

        label.setBackground(Color.black);
        label.setFont(Fonts.getFont(24));
        label.setForeground(color);

    }
*/
    /**
     * Odpowiada za kolor, czcionkę i wygląd pól tekstowych w oknie przed grą
     */
    private void textFieldCustomizer(JTextField textField, Color color){

        textField.setBackground(Color.black);
        textField.setFont(Fonts.getFont(24));
        textField.setForeground(color);

    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi START
     */
    private ActionListener startButtonListener(JTextField enterName) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nick = enterName.getText();
                add(new Level(getWidth(),getHeight(), 1, PropertiesLoad.numberOfLives, 0),buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi BACK
     */
    private ActionListener backButtonListener() {
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
