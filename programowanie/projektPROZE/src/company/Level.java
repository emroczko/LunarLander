package company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za rysowanie poziomu ziemi oraz statku gracza
 */

public class Level extends JPanel{
    private ImageIcon backgroundImage;
    private ImageIcon landersLeftIcon;
    private Timer timer;
    private Lander lander;
    private boolean inGame = true;
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE_UP = "move up";
    private static final String MOVE_LEFT = "move left";
    private static final String MOVE_RIGHT = "move right";
    private static final String MOVE_DOWN = "move down";

    int a,b;


    public Level(int xSize, int ySize) {
        this.removeAll();

        setPreferredSize(new Dimension(xSize, ySize));


        try {
            PropertiesLoad.loadMapsConfigs(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        revalidate();
        repaint();
        initializeVariables();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;


        JButton exitButton = new JButton("EXIT");
        JButton pauseButton = new JButton("||");
        JButton continueButton = new JButton("CONTINUE");
        JLabel leftLandersLabel = new JLabel(": 4");
        JLabel vy = new JLabel("V. Speed: 100");
        JLabel vx = new JLabel("H. Speed: 100");
        JLabel time = new JLabel("Time: 60");
        JLabel emptyLabel = new JLabel("  ");
        JLabel landersLeft = new JLabel(this.landersLeftIcon = ImageFactory.createImage(Image.Lander));

        Font font = new Font("uni 05_53", Font.PLAIN, 40);

        buttonCustomizer(continueButton, font, false, Color.BLUE);
        buttonCustomizer(exitButton, font, false, Color.BLUE);
        buttonCustomizer(pauseButton, font, true, Color.lightGray);


        pauseButton.addActionListener(pauseButtonListener(continueButton, exitButton, pauseButton));
        continueButton.addActionListener(continueButtonListener(continueButton, exitButton, pauseButton));
        exitButton.addActionListener(exitButtonListener());

        keyBindings(this, 38, MOVE_UP);
        keyBindings(this, 40, MOVE_DOWN);
        keyBindings(this, 39, MOVE_RIGHT);
        keyBindings(this, 37, MOVE_LEFT);


        Font font1 = new Font("uni 05_53", Font.PLAIN, 20);
        Font font2 = new Font("uni 05_53", Font.PLAIN, 10);


        labelCustomizer(vx, font1, true, Color.lightGray);
        labelCustomizer(vy, font1, true, Color.lightGray);
        labelCustomizer(time, font1, true, Color.lightGray);
        labelCustomizer(leftLandersLabel, font1, true, Color.lightGray);

        gbc.gridx = 7;
        gbc.gridy = 3;
        gbc.weighty = 0.005;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(emptyLabel, gbc);

        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(pauseButton, gbc);

        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(landersLeft, gbc);

        gbc.gridx = 8;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(leftLandersLabel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(vx, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(vy, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(time, gbc);

        this.add(exitButton);
        this.add(continueButton);



    }
    /** Funkcja inicjująca zmienne klasy*/
    private void initializeVariables(){
        
        setFocusable(true);
        this.lander = new Lander();
        this.backgroundImage = ImageFactory.createImage(Image.Earth1);
        this.timer = new Timer(10, new GameLoop(this));
        this.timer.start();


    }
    /** Funkcja pauzująca grę*/
    private void pause(boolean condit){
        if (condit){

        }
        else{

        }
    }
    /**Funkcja odpowiedzialna za rysowanie obrazku reprezentującego gracza oraz jego hitboxa oraz skalowanie rozmiarów
     * tych elementów poprzez mnożenie ich wielkości i położenia przez współczynnik skali będący stosunkiem obecnej wielkośi
     * okna do jego początkowej wielkości wczytywanej z pliku konfiguracyjnego
     */
    private void drawPlayer(Graphics g){
        g.drawImage(lander.getImage(), (int)(lander.getX()*((float)(this.getWidth())/PropertiesLoad.xSize)),
                (int)(lander.getY()*((float)this.getHeight()/PropertiesLoad.ySize)), (int)(this.getWidth()/17.5),
                (int)(this.getHeight()/12.5), this);

        g.setColor(Color.red);
        g.drawRect((int)(lander.getX()*((float)(this.getWidth())/PropertiesLoad.xSize)),
                (int)(lander.getY()*((float)this.getHeight()/PropertiesLoad.ySize)),
                (int)(40*((float)getWidth()/PropertiesLoad.xSize)),(int)(40*((float)getHeight()/PropertiesLoad.ySize)));
    }

    /**
     * Rysuje hitbox powirzchni księzyca i lądowiska oraz skaluje do rozmiarów okna
     */
    private void drawGround(Graphics g){
        Polygon moon = new Polygon(scalePoints(PropertiesLoad.xPoints, 'x'), scalePoints(PropertiesLoad.yPoints, 'y'),
                PropertiesLoad.xPoints.length);
        Polygon landing = new Polygon(scalePoints(PropertiesLoad.xLanding, 'x'), scalePoints(PropertiesLoad.yLanding, 'y'), PropertiesLoad.xLanding.length);
        g.setColor(Color.green);
        g.drawPolygon(moon);
        g.setColor(Color.blue);
        g.drawPolygon(landing);
    }


    /**
     * Funkcja skalująca zadaną tablicę intów w zależności od rozmiarów okna
     * @param points tablica intów będąca reprezentacją punktów uzywanych w metodzie drawGround do rysowania powieżchni księżyca
     * @param param zmianna char mówiąca o tym czy punkty które skalujemy są punktami z osi OX czy OY
     * @return zwraca przeskalowaną tablicę intów
     */
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
            //System.out.println((float)(getWidth())/PropertiesLoad.xSize);
        }

        return scaled_points;
    }

    /**
     * metoda nadpisująca metodę paintComponent w celu przeskalowania obrazka w tle oraz rysowania obiektów z gry
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        doDrawing(g);
    }

    /**
     *Funkcja rysująca obiekty gry(gracz i powierzchnia księżyca)
     */
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

    /**
     * Aplikuje zmiany wykonane przez gracza oraz odświeża okno gry
     */
    public void doOneLoop(){
        this.update();
        this.repaint();
    }

    private void update(){
        this.lander.update();
    }
    /**
     * Odpowiada za kolor, czcionkę i wygląd przycisków w oknie gry
     */
    private void buttonCustomizer(JButton button, Font font, boolean visible, Color color){

        button.setFont(font);
        button.setForeground(color);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(visible);

    }

    /**
     * Odpowiada za kolor, czcionkę i wygląd napisów informacyjnych w oknie gry
     */
    private void labelCustomizer(JLabel label, Font font, boolean visible, Color color){

        label.setBackground(Color.black);
        label.setFont(font);
        label.setForeground(color);

    }
    /**
     * Odpowiada za przypisanie akcji przyciskowi || (pauza)
     */
    private ActionListener pauseButtonListener(JButton continueButton, JButton exitButton, JButton pauseButton) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continueButton.setVisible(true);
                exitButton.setVisible(true);
                pauseButton.setVisible(false);
                pause(true);
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi CONTINUE
     */
    private ActionListener continueButtonListener(JButton continueButton, JButton exitButton, JButton pauseButton) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pauseButton.setVisible(true);
                pause(false);
                continueButton.setVisible(false);
                exitButton.setVisible(false);
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi EXIT
     */
    private ActionListener exitButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                repaint();
                revalidate();
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.BOTH;
                add(new Menu(), gbc);
            }
        };
        return actionListener;
    }
    /**
     * Odpowiada za wybranie odpowiedniej metody dla klikniętego klawisza
     */
    private Action action(String action){
        Action newAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                switch(action){
                    case "move up": lander.moveUp();
                        break;
                    case "move left": lander.moveLeft();
                        break;
                    case "move right": lander.moveRight();
                        break;
                    case "move down": lander.moveDown();
                        break;
                }
            }
        };
        return newAction;
    }
    /**
     * Odpowiada za obłsugę klawiszy
     */
    private void keyBindings(Level level, int keyCode, String keyName){
        level.getInputMap(IFW).put(KeyStroke.getKeyStroke(keyCode, 0,false), keyName);
        level.getActionMap().put(keyName, action(keyName));
    }

    /*
    public void keyReleased(KeyEvent e){
        System.out.println("Level key released");
        this.lander.keyReleased(e);
    }
    public void keyPressed(KeyEvent e){
        System.out.println("Level key pressed");
        this.lander.keyPressed(e);
    }
    */



}



