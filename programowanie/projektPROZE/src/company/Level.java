package company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.*;

import java.util.ArrayList;


/**
 * Klasa odpowiedzialna za rysowanie poziomu ziemi oraz statku gracza
 */
public class Level extends JPanel{

    private ImageIcon backgroundImage;
    private ImageIcon landersLeftIcon;
    private Image currentImage;
    /** Obiekt klasy Timer**/
    private Timer timer;
    /** Obiekt klasy Lander**/
    private Lander lander;
    private boolean inGame = true;
    /** Zmienna wykorzystywana do KeyBindings**/
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    /** String przechowujący nazwę przycisku używany do KeyBindings**/
    private static final String MOVE_UP = "move up";
    /** String przechowujący nazwę przycisku używany do KeyBindings**/
    private static final String MOVE_LEFT = "move left";
    /** String przechowujący nazwę przycisku używany do KeyBindings**/
    private static final String MOVE_RIGHT = "move right";
    /** String przechowujący nazwę przycisku używany do KeyBindings**/
    private static final String MOVE_DOWN = "move down";
    /** Numer obecnego poziomu**/
    private int levelNum;
    /** Ilość pozostałych żyć**/
    private int leftLives;
    /** Ilość pozostałego czasu**/
    private int time = 60;
    private int asteroid_counter;
    protected float fuelLevel;
    private String nick;
    private ArrayList<Asteroid> asteroids;
    /** Ilość punktów**/
    private int points;
    private int prevPoints;
    JLabel vx = new JLabel("H. Speed: 0");
    JLabel vy = new JLabel("V. Speed: 0");
    JLabel leftLandersLabel = new JLabel();
    JLabel fuelLabel = new JLabel("Fuel");
    JLabel timeLabel = new JLabel("Left time: 60 sec");
    JProgressBar fuelBar = new JProgressBar();
    /** Kolor niebieski używany w oknie*/
    Color aqua = new Color (51, 134, 175);
    /** Kolor żółty używany w oknie*/
    Color citron = new Color (223, 234, 24);

    ButtonCustomizer customButtonTrue = new ButtonCustomizer(true, Color.lightGray, 40);
    ButtonCustomizer customButtonFalse = new ButtonCustomizer(false, Color.BLUE, 40);
    LabelCustomizer custom = new LabelCustomizer(Color.lightGray, 20);
    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    ScheduledExecutorService boomExecutor = Executors.newScheduledThreadPool(1);


    public Level(int xSize, int ySize, int levelNumber, int Lives, int previousPoints, String nickName, ImageIcon background) {
        this.removeAll();
        this.repaint();
        this.revalidate();

        nick = nickName;
        levelNum = levelNumber;
        leftLives = Lives;
        prevPoints = previousPoints;
        setPreferredSize(new Dimension(xSize, ySize));
        keyBindings(this, 32, "nothing");


        try {
            PropertiesLoad.loadMapsConfigs(levelNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.backgroundImage = background;


        initializeVariables(levelNumber);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;

        JButton exitButton = new JButton("EXIT");
        JButton pauseButton = new JButton("||");
        JButton continueButton = new JButton("CONTINUE");
        JLabel landersLeft = new JLabel(this.landersLeftIcon = ImageFactory.createImage(Image.Lander));

        labelUpdate("lives");

        customButtonTrue.customizer(pauseButton);
        customButtonFalse.customizer(continueButton);
        customButtonFalse.customizer(exitButton);

        pauseButton.addActionListener(pauseButtonListener(continueButton, exitButton, pauseButton));
        continueButton.addActionListener(continueButtonListener(continueButton, exitButton, pauseButton));
        exitButton.addActionListener(exitButtonListener());

        keyBindings(this, 38, MOVE_UP);
        keyBindings(this, 40, MOVE_DOWN);
        keyBindings(this, 39, MOVE_RIGHT);
        keyBindings(this, 37, MOVE_LEFT);

        custom.customizer(vx);
        custom.customizer(vy);
        custom.customizer(timeLabel);
        custom.customizer(leftLandersLabel);
        custom.customizer(fuelLabel);
        custom.customizer(timeLabel);

        fuelBar.setVisible(true);
        fuelBar.setValue(100);
        fuelBar.setBorderPainted(false);
        fuelBar.setStringPainted(false);
        fuelBar.setForeground(citron);
        fuelBar.setBackground(aqua);



        this.add(vx, customGBC.gbcCustomize(0,0,0,0,1, "FIRST_LINE_START"));
        this.add(vy, customGBC.gbcCustomize(0,1,0,0,1, "FIRST_LINE_START"));
        this.add(fuelBar, customGBC.gbcCustomize(2,0,0,0,1, "FIRST_LINE_END"));
        this.add(fuelLabel, customGBC.gbcCustomize(1,0,1,0,1, "FIRST_LINE_END"));

        this.add(timeLabel, customGBC.gbcCustomize(0,2,0,1,1, "FIRST_LINE_START"));
        this.add(pauseButton, customGBC.gbcCustomize(2,2,0,0,1, "FIRST_LINE_END"));
        this.add(exitButton,customGBC.gbcCustomize(0,3,0,0,1, "LAST_LINE_START"));
        this.add(continueButton, customGBC.gbcCustomize(1,3,0,0,2, "LAST_LINE_END"));
        this.add(leftLandersLabel, customGBC.gbcCustomize(2,1,0,0,1, "FIRST_LINE_END"));
        this.add(landersLeft, customGBC.gbcCustomize(2,1,0,0,1, "CENTER"));

    }
    /** Funkcja inicjująca zmienne klasy*/
    private void initializeVariables(int levelNumber){

        setFocusable(true);
        asteroid_counter = 0;
        this.lander = new Lander(this);
        this.lander.landerImageChange(Image.Lander);
        this.asteroids = new ArrayList<Asteroid>();
        this.fuelLevel = PropertiesLoad.fuelAmount;


        this.timer = new Timer(40, new GameLoop(this));
        this.timer.start();
        timeCounter(true);


    }



    /** Funkcja pauzująca grę*/
    private void pause(){
        this.timer.stop();
        timeCounter(false);
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
        timeCounter(true);

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
        detectCollision(landing, moon);
    }

    private void drawAsteroid(Graphics g){
        for (int i =0; i<asteroids.size(); i++)
        {
            g.drawImage(asteroids.get(i).getImage(),
                    (int)(asteroids.get(i).getX()*((float)(this.getWidth())/PropertiesLoad.xSize)), (int)(asteroids.get(i).getY()*((float)this.getHeight()/PropertiesLoad.ySize)),
                    (int)(20*((float)this.getWidth()/PropertiesLoad.xSize)), (int)(20*((float)this.getHeight()/PropertiesLoad.ySize)), this);
        }
    }

    private void addAsteroid(){
        Random rand = new Random();
        if (rand.nextInt(101)<4) {
            int direction = rand.nextInt(2);
            if (asteroid_counter < levelNum+2) {
                int velx = rand.nextInt(3) + 1;
                int vely = rand.nextInt(3) + 1;
                if (direction == 0) {
                    try {
                        int start_x = rand.nextInt(this.getWidth() / 2 - (int) (this.getWidth() / (17.5 * 2))) + this.getWidth() / 2 + (int) (this.getWidth() / (17.5 * 2));
                        this.asteroids.add(new Asteroid(start_x, -20, velx, vely, direction, this));
                    }
                    catch(Exception e){}
                } else {
                    try {
                        int start_x = rand.nextInt(this.getHeight() / 2 - (int) (this.getHeight() / (17.5 * 2)));
                        this.asteroids.add(new Asteroid(start_x, -20, velx, vely, direction, this));
                    }
                    catch(Exception e){}

                }
                //asteroid_counter +=1;
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
        setDoubleBuffered(true);
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
            drawAsteroid(g);
        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            Toolkit.getDefaultToolkit().sync();
        }


    }

    /**
     *Metoda odpowiadająca za odliczanie w oknie gry
     */
    private void timeCounter(boolean onOff)
    {
        Runnable timeOn = () -> {
            time -= 1;
           labelUpdate("time");
        };

        if(onOff){
            executor.scheduleAtFixedRate(timeOn, 1, 1, SECONDS);
        }
        else {
            executor.shutdown();
            newExecutor("executor");
        }
    }

    /**
     * Metoda tworząca nowy obiekt ScheduledExecutorService i przypisująca go do obiektu który został zadeklarowany w klasie Level, który został wyłączony
     */
    private void newExecutor(String whichExecutor){
        ScheduledExecutorService newExecutor = Executors.newScheduledThreadPool(1);
        if(whichExecutor == "executor") {
            executor = newExecutor;
        }
        else{
            boomExecutor = newExecutor;
        }
    }
    /**
     * Wykrywanie kolizji i wywołanie odpowiednych metod
     * @param landing- wielokąt strefy lądowania
     * @param moon - wielokąt obszaru księzyca poza strefą lądowania
     */
    private void detectCollision(Polygon landing, Polygon moon){
        if(moon.intersects(lander.getRect())) {
            boom();
        }
        if(landing.intersects(lander.getRect())) {
            goodLanding();
        }
        asteroidsCollision(landing, moon);
    }

    private void asteroidsCollision(Polygon landing, Polygon moon){
        for(int i = 0; i<this.asteroids.size();i++){

            if (lander.getRect().intersects(asteroids.get(i).getRect())){
                boom();
            }

            if (moon.intersects(asteroids.get(i).getRect()) || landing.intersects(asteroids.get(i).getRect())){
                asteroids.remove(i);
            }
            if (asteroids.size()==0){
                break;
            }
            if (i+1<this.asteroids.size()){
                    for(int j=i+1; j<this.asteroids.size(); j++) {
                        if (asteroids.get(i).getRect().intersects(asteroids.get(j).getRect())) {
                            asteroids.remove(i);
                        }
                    }
            }
        }
    }

    /**
     * Metoda która zatrzymuje grę na pewien czas i zmienia ikonę statku na wybuch
     */
    private void boom(){
        this.lander.landerImageChange(Image.Boom);
        this.timer.stop();
        BoomCounter();
    }

    /**
     *Metoda odpowiadająca za odliczanie w oknie gry
     */
    private void BoomCounter()
    {
        Runnable crash = this::wreckedShip;
        try {
            boomExecutor.schedule(crash, 1, SECONDS);
            boomExecutor.shutdown();
        }
        catch(Exception E){
    }
    }

    /**
     * Metoda która definiuje zachowanie okna po udanym lądowaniu
     */
    private void goodLanding(){
        if (lander.velx < 7 && lander.vely < 7) {
            countPoints();
            if (levelNum != PropertiesLoad.numberOfLevels) {
                cleanWindow();
                //add(new WonLevel(getWidth(), getHeight(), levelNum, leftLives, points, nick), newWindow.buttonsClickedBehaviour());
                add(new IntroLevel(getWidth(), getHeight(), levelNum+1, leftLives, points, nick), newWindow.buttonsClickedBehaviour());
            } else {
                cleanWindow();
                add(new WonGame(getWidth(), getHeight(), nick, points), newWindow.buttonsClickedBehaviour());
            }
        } else {
            boom();
        }
    }
    /**
     * Metoda która definiuje zachowanie okna po rozbiciu statku, w zalezności od ilości żyć
     */
    private void wreckedShip(){
        if(leftLives == 0) {
            countPoints();
            cleanWindow();
            add(new LostGame(getWidth(), getHeight(), points, nick), newWindow.buttonsClickedBehaviour());
        }
        else{
            cleanWindow();
            add(new Level(getWidth(), getHeight(), levelNum, leftLives - 1, points, nick, this.backgroundImage), newWindow.buttonsClickedBehaviour());
        }
    }
    /**
     * Odpowiada za wywołanie odpowiedniej funkcji (outOfLives) gdy statkowi zabraknie paliwa
     */
    protected void noFuel(){
        if (fuelLevel <= 0){
            for(int i = 37; i<41; i++){
                keyBindings(this, i, "nothing");
            }
        }
    }

    /**
     * Odpowiada za zliczanie punktów
     */
    private void countPoints(){
        points = (5 * (int)fuelLevel) + (5 * time) + prevPoints;
    }
    /**
     * Aplikuje zmiany wykonane przez gracza oraz odświeża okno gry
     */
    public void doOneLoop(){
        addAsteroid();
        this.update();
        this.repaint();
    }

    private void update(){
        this.lander.update();
        this.lander.setLevel(this);
        updateAsteroids();
    }
    private void updateAsteroids(){
        for (int i =0; i<asteroids.size(); i++){
            asteroids.get(i).update();
        }
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
                cleanWindow();
                timer.stop();
                add(new Menu(), newWindow.buttonsClickedBehaviour());
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

    public void BarUpdate(){
        fuelBar.setValue((int)fuelLevel);
        super.update(this.getGraphics());
    }
    /**
     * Odpowiada za wywołanie metody obiektu klasy NewWindow służącej do usunięcia wszystkich elemntów z obecnego JPanelu
     */
    private void cleanWindow(){
        newWindow.layoutMakerLevel(this);
    }


}



