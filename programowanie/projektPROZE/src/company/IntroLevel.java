package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class IntroLevel extends JPanel{

    private ImageIcon backgroundImage;
    /**
     * Zmienna wykorzystywana do KeyBindings
     **/
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    /**
     * String przechowujący nazwę przycisku używany do KeyBindings
     **/
    private static final String SPACE = "space";
    /**
     * Ilość punktów
     **/
    private int points;
    private int level;
    private int lives;
    private String nick;
    JLabel levelLabel = new JLabel();
    JLabel spaceLabel = new JLabel("Press space to begin...");
    /**
     * Kolor niebieski używany w oknie
     */
    Color aqua = new Color(51, 134, 175);
    /** Obiekt klasy LabelCustomizer **/
    LabelCustomizer customLabel = new LabelCustomizer(aqua, 54);

    LabelCustomizer customLabelSpace = new LabelCustomizer(aqua, 40);
    int a,b;




    /**
     * Kolor żółty używany w oknie
     */
    Color citron = new Color(223, 234, 24);

    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    public IntroLevel(int xSize, int ySize, int levelNumber, int livesNumber, int previousPoints, String nickName) {

        this.removeAll();
        repaint();
        revalidate();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));
        this.setLayout(new GridBagLayout());

        nick = nickName;
        points = previousPoints;
        level = levelNumber;
        lives = livesNumber;
        keyBindings(this, 32);
        setBackground(levelNumber);
        customLabel.customizer(levelLabel);
        customLabelSpace.customizer(spaceLabel);
        levelLabel.setText("LEVEL"+ levelNumber);

        this.add(levelLabel, customGBC.gbcCustomize(0,1,0,0,0, "SOUTH"));
        this.add(spaceLabel, customGBC.gbcCustomize(0,2,0,0,0, "SOUTH"));

    }




    private void setBackground(int levelNumber) {
        switch (levelNumber) {
            case 1:
                this.backgroundImage = ImageFactory.createImage(Image.Earth1);
                break;
            case 2:
                this.backgroundImage = ImageFactory.createImage(Image.Mars1);
                break;
            case 3:
                this.backgroundImage = ImageFactory.createImage(Image.Jupiter1);
                break;
            case 4:
                this.backgroundImage = ImageFactory.createImage(Image.Saturn1);
                break;
            case 5:
                this.backgroundImage = ImageFactory.createImage(Image.Earth2);
                break;
            case 6:
                this.backgroundImage = ImageFactory.createImage(Image.Mars2);
                break;
            case 7:
                this.backgroundImage = ImageFactory.createImage(Image.Jupiter2);
                break;
            case 8:
                this.backgroundImage = ImageFactory.createImage(Image.Saturn2);
                break;
        }
    }
    /**
     * metoda nadpisująca metodę paintComponent w celu przeskalowania obrazka w tle oraz rysowania obiektów z gry
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    private Action action(){
        Action newAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                startLevel();
            }
        };
        return newAction;
    }
    /**
     * Odpowiada za obłsugę klawiszy
     */
    private void keyBindings(IntroLevel introLevel, int keyCode){
        introLevel.getInputMap(IFW).put(KeyStroke.getKeyStroke(keyCode, 0,false), SPACE);
        introLevel.getActionMap().put(SPACE, action());
    }
    private void startLevel(){
        cleanWindow();
        add(new Level(getWidth(),getHeight(), level, lives, points, nick),newWindow.buttonsClickedBehaviour());
    }
    private void cleanWindow(){
        newWindow.layoutMakerIntroLevel(this);
    }
}
