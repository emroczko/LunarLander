package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.util.Timer;

public class MainMenu extends JFrame {
    /**
     *
     */

    BufferedImage img;
    private static final long serialVersionUID = 1L;
    private String nick;
    private int levelNumber;
    private Timer timer;

    public MainMenu()
    {

        Frame test =new JFrame();
        String bg_path = "Images/MainMenu.png";
        //load_image(bg_path);

        timer = new Timer();

        Container components_container = new JLabel();

        components_container.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();


        Button b_play = new Button("Play");
        b_play.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {
                                    nickScreen();
                                     }
                                 });

        Button b_best_scores = new Button("Best Scores");
        b_best_scores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bestScoreScreen();
            }
        });



        Button b_instructions = new Button("Instructions");


        Font font = new Font("uni 05_53", Font.PLAIN, 20);

        b_play.setFont(font);
        b_play.setBackground(Color.black);
        //b_play.setMnemonic(KeyEvent.VK_D);
        b_play.setActionCommand("disable");

        b_best_scores.setFont(font);
        b_best_scores.setBackground(Color.black);
       // b_best_scores.setMnemonic(KeyEvent.VK_M);
        b_best_scores.setVisible(true);

        b_instructions.setFont(font);
        b_instructions.setBackground(Color.black);

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        components_container.add(b_play, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        components_container.add(b_best_scores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        components_container.add(b_instructions, gbc);

        this.setSize(700,500);
        this.add(components_container);
        this.setVisible(true);

        this.repaint();
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {System.exit(0);
            }
        });
    }
    void bestScoreScreen(){
        this.getContentPane().removeAll();
        //load_image("Images/SaturnMoon.png");
        Container components_container = new JLabel(new ImageIcon("Images/SaturnMoon.png"));


        //Button button = new Button("chuj");
        this.getContentPane().setLayout(new FlowLayout());

        JButton button1 = new JButton();
        button1.setText("Java Code Geeks");

        JButton button2 = new JButton("Java Examples & Code Snippets");

        // add buttons to frame
        add(button1);
        add(button2);
        this.repaint();
        this.setVisible(true);

    }
    void nickScreen(){
        this.getContentPane().removeAll();
        this.repaint();
        this.setLayout(new GridBagLayout());

        String bg_path = "Images/MainMenu.png";
        Container components_container = new JLabel(new ImageIcon(bg_path));

        components_container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

            Label typeNick=new Label("Type your nick");
        TextField enterName = new TextField("Your nick...");
        Button b_start = new Button("Start!");
        b_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nick = enterName.getText();
                loadLevel(1);


            }
        });
        Button b_back = new Button("Back");
        b_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
               MainMenu win =  new MainMenu();
            }
        });


        Font font = new Font("uni 05_53", Font.PLAIN, 20);

        typeNick.setBackground(Color.black);
        typeNick.setFont(font);
        typeNick.setForeground(Color.lightGray);

        enterName.setBackground(Color.black);
        enterName.setFont(font);
        enterName.setForeground(Color.YELLOW);
        //enterName.setHorizontalAlignment(JTextField.CENTER);

        b_start.setFont(font);
        b_start.setBackground(Color.black);

        b_back.setFont(font);
        b_back.setBackground(Color.black);



        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        components_container.add(typeNick, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;

        components_container.add(enterName, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        components_container.add(b_start, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        components_container.add(b_back, gbc);
        this.add(components_container);
        this.setVisible(true);
    }
    void loadLevel(int levelNumber){
        this.getContentPane().removeAll();
        this.repaint();
        this.reset_timer();
        timer = new Timer();
        this.setLayout(new GridBagLayout());

        String imgPath = whichLevel(levelNumber);
        //load_image(imgPath);
        Container components_container = new JLabel();

        components_container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Level level = new Level(levelNumber);
        //timer.scheduleAtFixedRate(level.new Animation_Timer(), 100, 30);
        this.add(components_container);
        this.add(level);
        this.setVisible(true);
    }


    String whichLevel(int levelNumber){
        String imgPath;

        switch (levelNumber) {
            case 1:
                imgPath = "Images/SaturnMoon.png";
                break;
            case 2:
                imgPath = "Images/MainMenu.png";
                break;
            case 3:
                imgPath = "Images/EarthMoon.png";
                break;
            case 4:
                imgPath = "Images/SaturnMoon.png";
                break;
            case 5:
                imgPath = "Images/SaturnMoon.png";
                break;
            case 6:
                imgPath = "Images/MainMenu.png";
                break;
            case 7:
                imgPath = "Images/SaturnMoon.png";
                break;
            case 8:
                imgPath = "Images/SaturnMoon.png";
                break;
            default:
                imgPath = "Images/SaturnMoon.png";
        }
        return imgPath;
    }
        /*this.getContentPane().removeAll();

        this.setLayout(new GridBagLayout());



        load_image(imgPath);
        this.repaint();


        Container sideMenu = new JLabel(new ImageIcon(imgPath));
        sideMenu.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //Label vspeed = new Label("V. speed: ");


        sideMenu.add(vx);
        sideMenu.add(vy);
        sideMenu.add(exitButton);

        sideMenu.add(spaceships);
        sideMenu.add(fuelLabel);
        //components_container1.add(vspeed);
        Font font = new Font("uni 05_53", Font.PLAIN, 20);

        pauseButton.setBackground(Color.black);
        pauseButton.setFont(font);
        pauseButton.setForeground(Color.lightGray);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        sideMenu.add(pauseButton,gbc);
        Level level = new Level(levelNumber);
        timer.scheduleAtFixedRate(level.new Animation_Timer(), 100, 30);
        this.setVisible(true);
    }*/
    public void load_image(String path)
    {
        try
        {
            img =  ImageIO.read(new File(path));
            //System.out.println(img);
        }
        catch(Exception e){e.printStackTrace();}
    }

    private void reset_timer()
    {
        timer.cancel();
        timer.purge();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img, 0, 0,getWidth(), getHeight(),this);

    }


}
