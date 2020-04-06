package company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiedzialna za rysowanie poziomu ziemi oraz statku gracza
 */

public class Level extends JPanel{
    private ImageIcon backgroundImage;
    private Timer timer;
    private Lander lander;
    private boolean inGame = true;
    int a,b;


    public Level(int xSize, int ySize) {

        this.removeAll();

        setPreferredSize(new Dimension(xSize,ySize));


        try {
            PropertiesLoad.loadMapsConfigs(1);
        }
        catch(Exception e){e.printStackTrace();}

        revalidate();
        repaint();
        initializeVariables();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;



        JButton exitButton = new JButton("EXIT");
        JButton pauseButton =  new JButton("||");
        JButton continueButton =  new JButton("CONTINUE");
        //JLabel pauseLabel = new JLabel("PAUSE");
        JLabel vy = new JLabel("V. Speed: 100");
        JLabel vx = new JLabel("H. Speed: 100");
        JLabel time = new JLabel("Time: 60");
        JLabel spaceships = new JLabel("Pozostałe statki: 99");

        Font font1 = new Font("uni 05_53", Font.PLAIN, 40);


        continueButton.setFont(font1);
        continueButton.setForeground(Color.BLUE);
        continueButton.setOpaque(false);
        continueButton.setContentAreaFilled(false);
        continueButton.setBorderPainted(false);
        continueButton.setVisible(false);

        exitButton.setFont(font1);
        exitButton.setForeground(Color.BLUE);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setVisible(false);

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continueButton.setVisible(true);
                exitButton.setVisible(true);
                pauseButton.setVisible(false);
                pause(true);
            }
        });
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseButton.setVisible(true);
                pause(false);
                continueButton.setVisible(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
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
        Font font2 = new Font("uni 05_53", Font.PLAIN, 10);

        pauseButton.setFont(font1);
        pauseButton.setForeground(Color.lightGray);
        pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);

        vx.setBackground(Color.black);
        vx.setFont(font);
        vx.setForeground(Color.lightGray);

        vy.setBackground(Color.black);
        vy.setFont(font);
        vy.setForeground(Color.lightGray);

        time.setBackground(Color.black);
        time.setFont(font);
        time.setForeground(Color.lightGray);


        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(pauseButton,gbc);



        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(vx,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(vy,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(time,gbc);

        this.add(exitButton);
        this.add(continueButton);

    }

    private void initializeVariables(){
        this.lander = new Lander();
        this.backgroundImage = ImageFactory.createImage(Image.Earth1);
        this.timer = new Timer(10, new GameLoop(this));
        this.timer.start();

    }

    private void pause(boolean condit){
        if (condit){

        }
        else{

        }
    }

    private void drawPlayer(Graphics g){
        g.drawImage(lander.getImage(), (int)(lander.getX()*((float)(this.getWidth())/700)),
                (int)(lander.getY()*((float)this.getHeight()/500)), (int)(this.getWidth()/17.5),
                (int)(this.getHeight()/12.5), this);
        g.setColor(Color.red);
        g.drawRect(lander.getX(), lander.getY(), 40, 40);
    }
    private void drawGround(Graphics g){
        Polygon moon = new Polygon(PropertiesLoad.xPoints, PropertiesLoad.yPoints, PropertiesLoad.xPoints.length);
        Polygon landing = new Polygon(PropertiesLoad.xLanding, PropertiesLoad.yLanding, PropertiesLoad.xLanding.length);
        g.setColor(Color.green);
        g.drawPolygon(moon);
        g.setColor(Color.blue);
        g.drawPolygon(landing);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            drawPlayer(g);
            drawGround(g);
        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }
    public void doOneLoop(){
        this.update();
        this.repaint();
    }

    private void update(){

    }


}



