package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Name extends JPanel{
    private ImageIcon MainMenuImage;
    private String nick;

    public Name(){

        this.removeAll();
        initializeLayout();
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
                add(new Level());
            }
        });
       backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeAll();
                repaint();
                revalidate();
                add(new Menu());
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


    }
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
    }
    private void initializeLayout() {
        setPreferredSize(new Dimension(PropertiesLoad.xSize, PropertiesLoad.ySize));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

    }
}
