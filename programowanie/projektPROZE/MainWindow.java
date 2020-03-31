import java.awt.event.WindowAdapter;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class MainWindow extends Frame {
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    MainWindow() {
        BufferedImage img = Toolkit.getDefaultToolkit().getImage("MainMenu.png");
        Panel panel = new Panel(new GridBagLayout());
        Panel panel_name = new Panel(null);
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
        panel.add(b_play, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        panel.add(b_best_scores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(b_instructions, gbc);
        this.setSize(700,500);
        this.add(panel_name);
        this.add(panel);
        this.setVisible(true);

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img,0,0,getWidth(), getHeight(), null);
    }
    this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
            System.exit(0);
         }
    });
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
    }

}