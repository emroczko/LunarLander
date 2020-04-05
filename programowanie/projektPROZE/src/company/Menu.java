package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{
    private ImageIcon MainMenuImage;

    public Menu() {
        this.removeAll();
        initializeLayout();
        initializeVariables();


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton startButton = new JButton("Start!");
        JButton rankingButton = new JButton("Best Scores");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                repaint();
                revalidate();
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.BOTH;
                add(new Name(getWidth(),getHeight()), gbc);

            }
        });
        Font font = new Font("uni 05_53", Font.PLAIN, 20);

        startButton.setFont(font);
        //startButton.setBackground(Color.black);
        //startButton.setForeground(Color.white);


        rankingButton.setFont(font);
        //rankingButton.setBackground(Color.black);
        //rankingButton.setForeground(Color.white);





        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(startButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        this.add(rankingButton, gbc);

        //gbc.gridx = 0;
        //gbc.gridy = 3;
        //components_container.add(b_instructions, gbc);





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
