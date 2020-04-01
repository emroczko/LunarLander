import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class MainMenu extends Frame {
    /**
     *
     */
    BufferedImage img;
    private static final long serialVersionUID = 1L;

    public MainMenu()
    {
        String bg_path = "Images/MainMenu.png";
        load_image(bg_path);

        Container components_container = new JLabel(new ImageIcon(bg_path));

        components_container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Button b_play = new Button("Play");
        Button b_best_scores = new Button("Best Scores");
        Button b_instructions = new Button("Instructions");

        Font font = new Font("Arial", Font.PLAIN, 20);
        b_play.setFont(font);
        b_best_scores.setFont(font);
        b_instructions.setFont(font);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        components_container.add(b_play, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        components_container.add(b_best_scores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        components_container.add(b_instructions, gbc);

        this.setSize(700,500);
        this.add(components_container);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    public void load_image(String path)
    {
        try
        {
            img =  ImageIO.read(new File(path));
            System.out.println(img);
        }
        catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        MainMenu win = new MainMenu();
    }

}
