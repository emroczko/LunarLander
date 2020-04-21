package company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.*;

import java.util.ArrayList;
import java.util.Random;



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
    JLabel vx = new JLabel();
    JLabel vy = new JLabel();
    JLabel leftLandersLabel = new JLabel();
    JLabel fuelLabel = new JLabel();
    JLabel timeLabel = new JLabel();
    JProgressBar fuel = new JProgressBar();
    private int levelNum;
    private int leftLives;
    protected float fuelLevel;
    private ArrayList<Asteroid> asteroids;
    private float points;
    private int time = 60;
    private int asteroid_counter;


    public Level(int xSize, int ySize, int levelNumber, int Lives, float previousPoints) {
        this.removeAll();
        levelNum = levelNumber;
        leftLives = Lives;
        points = previousPoints;
        setPreferredSize(new Dimension(xSize, ySize));


        try {
            PropertiesLoad.loadMapsConfigs(levelNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        revalidate();
        repaint();
        initializeVariables(levelNumber);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;


        JButton exitButton = new JButton("EXIT");
        JButton pauseButton = new JButton("||");
        JButton continueButton = new JButton("CONTINUE");


        JLabel time = new JLabel("Time: 60");
        JLabel emptyLabel = new JLabel("  ");
        JLabel landersLeft = new JLabel(this.landersLeftIcon = ImageFactory.createImage(Image.Lander));
        vx.setText("H. Speed: 0");
        vy.setText("V. Speed: 0");
        fuelLabel.setText("Fuel: 100");
        timeLabel.setText("Left time: 60 sec");
        labelUpdate("lives");
        timeCounter();

        buttonCustomizer(continueButton,  false, Color.BLUE);
        buttonCustomizer(exitButton, false, Color.BLUE);
        buttonCustomizer(pauseButton,  true, Color.lightGray);

        pauseButton.addActionListener(pauseButtonListener(continueButton, exitButton, pauseButton));
        continueButton.addActionListener(continueButtonListener(continueButton, exitButton, pauseButton));
        exitButton.addActionListener(exitButtonListener());

        keyBindings(this, 38, MOVE_UP);
        keyBindings(this, 40, MOVE_DOWN);
        keyBindings(this, 39, MOVE_RIGHT);
        keyBindings(this, 37, MOVE_LEFT);

        /*
        labelCustomizer(vy, true, Color.lightGray);
        labelCustomizer(time,  true, Color.lightGray);
        labelCustomizer(leftLandersLabel, true, Color.lightGray);
        labelCustomizer(fuelLabel, true, Color.lightGray);
        labelCustomizer(timeLabel, true, Color.lightGray);*/

        labelCustomizer custom = new labelCustomizer(Color.lightGray, 20);
        custom.customizer(vx);
        custom.customizer(vy);
        custom.customizer(time);
        custom.customizer(leftLandersLabel);
        custom.customizer(fuelLabel);
        custom.customizer(timeLabel);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 0.005;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(emptyLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(landersLeft, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(leftLandersLabel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx=0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(vx, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(vy, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(timeLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(pauseButton, gbc);
        this.add(exitButton);
        this.add(continueButton);
    }
    /** Funkcja inicjująca zmienne klasy*/
    private void initializeVariables(int levelNumber){

        setFocusable(true);
        asteroid_counter = 0;
        this.lander = new Lander(this);
        this.asteroids = new ArrayList<Asteroid>();
        this.fuelLevel = PropertiesLoad.fuelAmount;

        switch(levelNumber){
            case 1: this.backgroundImage = ImageFactory.createImage(Image.Earth1);
                break;
            case 2:  this.backgroundImage = ImageFactory.createImage(Image.Mars1);
                break;
            case 3:  this.backgroundImage = ImageFactory.createImage(Image.Jupiter1);
                break;
            case 4:  this.backgroundImage = ImageFactory.createImage(Image.Saturn1);
                break;
            case 5: this.backgroundImage = ImageFactory.createImage(Image.Earth2);
                break;
            case 6:  this.backgroundImage = ImageFactory.createImage(Image.Mars2);
                break;
            case 7:  this.backgroundImage = ImageFactory.createImage(Image.Jupiter2);
                break;
            case 8:  this.backgroundImage = ImageFactory.createImage(Image.Saturn2);
                break;
        }
        this.timer = new Timer(40, new GameLoop(this));
        this.timer.start();



    }
    /** Funkcja pauzująca grę*/
    private void pause(){
        this.timer.stop();
        for(int i = 37; i<41; i++){
            keyBindings(this, i, "nothing");
        }
    }
    /** Funkcja wznawiająca grę*/
    private void resume(){
        this.timer.start();
        keyBindings(this, 38, MOVE_UP);
        keyBindings(this, 40, MOVE_DOWN);
        keyBindings(this, 39, MOVE_RIGHT);
        keyBindings(this, 37, MOVE_LEFT);
    }
    /**Funkcja odpowiedzialna za rysowanie obrazku reprezentującego gracza oraz jego hitboxa oraz skalowanie rozmiarów
     * tych elementów poprzez mnożenie ich wielkości i położenia przez współczynnik skali będący stosunkiem obecnej wielkośi
     * okna do jego początkowej wielkości wczytywanej z pliku konfiguracyjnego
     */
    private void drawPlayer(Graphics g){
        g.drawImage(lander.getImage(), (int)(lander.getX()*((float)(this.getWidth())/PropertiesLoad.xSize)),
                (int)(lander.getY()*((float)this.getHeight()/PropertiesLoad.ySize)), (int)(this.getWidth()/17.5),
                (int)(this.getHeight()/12.5), this);
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
        detectCollision(landing, moon);
    }

    private void drawAsteroid(){

    }

    private void addasteroid(){
        Random rand = new Random();
        int direction = rand.nextInt(1);
        if (asteroid_counter<levelNum)
        {
            if (direction == 0){
                int velx = rand.nextInt(2)+1;
                int vely = rand.nextInt(2)+1;
                int start_x = rand.next(this.getWidth()/2 -((int)(lander.getX()*((float)(this.getWidth())/PropertiesLoad.xSize)))+
                this.asteroids.add(Asteroid(velx, vely, ))
            }

        }
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

    private void timeCounter(){
        Runnable helloRunnable = new Runnable() {
            public void run() {
               time -= 1;
               labelUpdate("time");
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 1, 1, SECONDS);
    }

    /**
     * Wykrywanie kolizji
     * @param landing- wielokąt strefy lądowania
     * @param moon - wielokąt obszaru księzyca poza strefą lądowania
     */
    private void detectCollision(Polygon landing, Polygon moon){
        if(moon.intersects(lander.getRect()))
        {

            if(leftLives == 0 ) {
                countPoints();
                add(new LostGame(getWidth(), getHeight(), points), buttonsClickedBehaviour());
            }
            else{
                add(new Level(getWidth(), getHeight(), levelNum ,leftLives-1, points), buttonsClickedBehaviour());
            }
        }
        if(landing.intersects(lander.getRect()))
        {
            countPoints();
            if(levelNum != 8) {
                add(new WonLevel(getWidth(), getHeight(), levelNum, leftLives, points), buttonsClickedBehaviour());
            }
            else{
                add(new WonGame(getWidth(), getHeight(), points), buttonsClickedBehaviour());
            }

        }
    }
    protected void noFuel(){
        if (fuelLevel == 0){
            if(leftLives == 0 ) {
                add(new LostGame(getWidth(), getHeight(), points), buttonsClickedBehaviour());
            }
            else{

                add(new Level(getWidth(), getHeight(), levelNum ,leftLives-1, points), buttonsClickedBehaviour());
            }
        }
    }
    private void countPoints(){
        points = (10 * fuelLevel) + (10 * time);
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
        this.lander.setLevel(this);

    }
    /**
     * Odpowiada za kolor, czcionkę i wygląd przycisków w oknie gry
     */
    private void buttonCustomizer(JButton button, boolean visible, Color color){

        button.setFont(Fonts.getFont(40));
        button.setForeground(color);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(visible);

    }

    /**
     * Odpowiada za kolor, czcionkę i wygląd napisów informacyjnych w oknie gry
     */
    private void labelCustomizer(JLabel label, boolean visible, Color color){

        label.setBackground(Color.black);
        label.setFont(Fonts.getFont(20));
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
                pause();
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
                resume();
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
                timer.stop();
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
                    case "nothing":
                        break;
                }
            }
        };
        return newAction;
    }
    /**
     * Odpowiada za wyczyszczenie ekranu i umieszczenie nowego okna po naciśnięciu któregoś z przycisków w oknie Name
     */
    private GridBagConstraints buttonsClickedBehaviour(){
        removeAll();
        repaint();
        revalidate();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }
    /**
     * Odpowiada za obłsugę klawiszy
     */
    private void keyBindings(Level level, int keyCode, String keyName){
        level.getInputMap(IFW).put(KeyStroke.getKeyStroke(keyCode, 0,false), keyName);
        level.getActionMap().put(keyName, action(keyName));
    }
    public void labelUpdate(String label){
        DecimalFormat df = new DecimalFormat("#.##");
        switch(label){
            case "vx": vx.setText("H. Speed: " + df.format(lander.velx));
            break;
            case "vy": vy.setText("V. Speed: " + df.format(lander.vely));
            break;
            case "lives": leftLandersLabel.setText(": " + leftLives);
            break;
            case "fuel": fuelLabel.setText("Fuel: "+ fuelLevel);
            break;
            case "time": timeLabel.setText("Left time: " + time+ " sec");
            break;
        }
        super.update(this.getGraphics());
    }


}



