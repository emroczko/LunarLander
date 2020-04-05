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

        JButton startButton = new JButton("Start!");
        JButton rankingButton = new JButton("Best Scores");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                repaint();
                revalidate();
                add(new Name());
            }
        });
        Font font = new Font("uni 05_53", Font.PLAIN, 20);

        startButton.setFont(font);
        //startButton.setBackground(Color.black);
        //startButton.setForeground(Color.white);


        rankingButton.setFont(font);
        //rankingButton.setBackground(Color.black);
        //rankingButton.setForeground(Color.white);


        this.add(startButton);
        this.add(rankingButton);




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
