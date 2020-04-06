package company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

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

        //this.setLayout(new GridBagLayout());
        //GridBagConstraints gbc = new GridBagConstraints();


        JButton exitButton = new JButton("EXIT");
        JButton pauseButton =  new JButton("||");
        JButton continueButton =  new JButton("CONTINUE");
        //JLabel pauseLabel = new JLabel("PAUSE");
        JLabel vy = new JLabel("PAUSE");
        JLabel vx = new JLabel("999999");
        JLabel fuelLabel = new JLabel("Paliwo: 99999");
        JLabel spaceships = new JLabel("Pozostałe statki: 99");
        Font font1 = new Font("uni 05_53", Font.PLAIN, 40);
        //pauseLabel.setFont(new Font("uni 05_53", Font.PLAIN, 40));
        //pauseLabel.setForeground(Color.WHITE);
        //pauseLabel.setOpaque(false);

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

        pauseButton.setFont(font);
        pauseButton.setForeground(Color.lightGray);
        pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        //gbc.gridwidth = 8;
        //gbc.gridwidth = 8;
        //gbc.gridx = 8;
        //gbc.gridy = -3;

        this.add(continueButton);
        this.add(pauseButton);
        this.add(exitButton);

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
        g.drawImage(lander.getImage(), (int)(lander.getX()*((float)(this.getWidth())/PropertiesLoad.xSize)),
                (int)(lander.getY()*((float)this.getHeight()/PropertiesLoad.ySize)), (int)(this.getWidth()/17.5),
                (int)(this.getHeight()/12.5), this);

        g.setColor(Color.red);
        g.drawRect((int)(lander.getX()*((float)(this.getWidth())/PropertiesLoad.xSize)),
                (int)(lander.getY()*((float)this.getHeight()/PropertiesLoad.ySize)),
                (int)(40*((float)getWidth()/PropertiesLoad.xSize)),(int)(40*((float)getHeight()/PropertiesLoad.ySize)));
    }
    private void drawGround(Graphics g){
        Polygon moon = new Polygon(scalePoints(PropertiesLoad.xPoints, 'x'), scalePoints(PropertiesLoad.yPoints, 'y'),
                PropertiesLoad.xPoints.length);
        Polygon landing = new Polygon(scalePoints(PropertiesLoad.xLanding, 'x'), scalePoints(PropertiesLoad.yLanding, 'y'), PropertiesLoad.xLanding.length);
        g.setColor(Color.green);
        g.drawPolygon(moon);
        g.setColor(Color.blue);
        g.drawPolygon(landing);
    }
    private int[] scalePoints(int[] points, char param)
    {
        int[] scaled_points = new int[points.length];
        for(int i=0; i<points.length; i++){
            if(param == 'y') {
                scaled_points[i] = (int)(points[i] * ((float) getHeight() / PropertiesLoad.ySize));
            }
            else {
                scaled_points[i] = (int)(points[i] * ((float) getWidth() / PropertiesLoad.xSize));
            }
            System.out.println((float)(getWidth())/PropertiesLoad.xSize);
        }

        return scaled_points;
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



