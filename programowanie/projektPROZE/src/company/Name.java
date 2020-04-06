package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton startButton = new JButton("Start!");
        JButton backButton = new JButton("Back");
        JLabel typeNick=new JLabel("Type your nick");
        JTextField enterName = new JTextField("Your nick...");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nick = enterName.getText();
                removeAll();
                repaint();
                revalidate();
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.BOTH;
                add(new Level(getWidth(),getHeight()),gbc);
            }
        });
       backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeAll();
                repaint();
                revalidate();
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.BOTH;
                add(new Menu(),gbc);
            }
        });
        Font font = new Font("uni 05_53", Font.PLAIN, 20);

        startButton.setFont(font);
        //startButton.setBackground(Color.black);
        //startButton.setForeground(Color.white);


        backButton.setFont(font);
        //rankingButton.setBackground(Color.black);
        //rankingButton.setForeground(Color.white);
        typeNick.setBackground(Color.black);
        typeNick.setFont(font);
        typeNick.setForeground(Color.lightGray);

        enterName.setBackground(Color.black);
        enterName.setFont(font);
        enterName.setForeground(Color.YELLOW);

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
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

    }
}
