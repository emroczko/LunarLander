package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Klasa odpowiedzialna za obsługę zdarzeń z graficznego interfejsu użytkownika, tutaj za pobranie nicku gracza i rozpoczęcie gry
 */

public class Name extends JPanel{
    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;
    /** Kolor niebieski używany w oknie*/
    Color aqua = new Color (51, 134, 175);
    /** Kolor żółty używany w oknie*/
    Color citron = new Color (223, 234, 24);
    /** Obiekt klasy LabelCustomizer **/
    LabelCustomizer customLabel = new LabelCustomizer(Color.lightGray, 36);
    /** Obiekt klasy ButtonCustomizer **/
    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);
    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();
    /** Przycisk Start **/
    JButton startButton = new JButton("Start!");
    /** Przycisk Back **/
    JButton backButton = new JButton("Back");
    /** Etykieta "Type your nick" **/
    JLabel typeNick=new JLabel("Type your nick:");
    /** Pole tekstowe do wpisania nicku gracza **/
    JTextField enterName = new JTextField("Your nick...");


    public Name(int xSize, int ySize){
        this.removeAll();
        repaint();
        revalidate();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));


        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();



        startButton.addActionListener(startButtonListener(enterName));
        backButton.addActionListener(backButtonListener());

        enterName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                enterName.setText("");
            }
        });

        customButton.customizer(startButton);
        customButton.customizer(backButton);

        customLabel.customizer(typeNick);

        textFieldCustomizer(enterName, aqua);

        this.add(typeNick, customGBC.gbcCustomize(0,1,0,0,3, "none"));
        this.add(enterName, customGBC.gbcCustomize(1,2,0,0,2, "none"));
        this.add(startButton, customGBC.gbcCustomize(2,3,0,0,2, "none"));
        this.add(backButton, customGBC.gbcCustomize(0,3,0,0,2, "none"));

    }
    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
    }
    /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

    }

    /**
     * Odpowiada za kolor, czcionkę i wygląd pól tekstowych w oknie przed grą
     */
    private void textFieldCustomizer(JTextField textField, Color color){

        textField.setBackground(Color.black);
        textField.setFont(Fonts.getFont(24));
        textField.setForeground(color);

    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi START
     */
    private ActionListener startButtonListener(JTextField enterName) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nick = enterName.getText();
                cleanWindow();
                add(new Level(getWidth(),getHeight(), 1, PropertiesLoad.numberOfLives, 0),newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi BACK
     */
    private ActionListener backButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Menu(),newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }
    /**
     * Odpowiada za wywołanie metody obiektu klasy NewWindow służącej do usunięcia wszystkich elemntów z obecnego JPanelu
     */
    private void cleanWindow(){
        newWindow.layoutMakerName(this);
    }
}
