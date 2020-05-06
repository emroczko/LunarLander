package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionWelcomeMenu extends JPanel{
    /** Zmienna przechowująca obrazek tła*/
    private ImageIcon MainMenuImage;
    /** Kolor niebieski używany w oknie*/
    Color aqua = new Color (51, 134, 175);
    /** Kolor żółty używany w oknie*/
    Color citron = new Color (200, 220, 24);
    /** Obiekt klasy ButtonCustomizer **/
    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);
    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();
    /** Przycisk Start **/
    JButton startOfflineButton = new JButton("Start offline...");
    /** Przycisk Best Scores **/
    JButton startOnlineButton = new JButton("Start online...");
    /** Przycisk Exit **/
    JButton exitButton = new JButton("Exit");

    /**konstruktor klasy*/
    public ConnectionWelcomeMenu(){
        this.removeAll();
        initializeLayout();
        initializeVariables();

        this.setLayout(new GridBagLayout());

        startOnlineButton.addActionListener(startOnlineListener());
        startOfflineButton.addActionListener(startOfflineListener());
        exitButton.addActionListener(exitButtonListener());


        customButton.customizer(startOfflineButton);
        customButton.customizer(startOnlineButton);
        customButton.customizer(exitButton);


        this.add(startOfflineButton, customGBC.gbcCustomize(0,1,0,0.4,0,"NORTH"));
        this.add(startOnlineButton, customGBC.gbcCustomize(2,1,0,0,0, "NORTH"));

        this.add(exitButton, customGBC.gbcCustomize(1,4,0,0.1,0, "NORTH"));
    }
    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
    }

    /** metoda ustawiająca rozmiar okna za pomocą danych z pliku Config.txt*/
    private void initializeLayout() {
        setPreferredSize(new Dimension(PropertiesLoad.xSize, PropertiesLoad.ySize));
    }

    /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle
     * @param g - obiekt klasy Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi Start online
     * @return actionListener - obiekt klasy ActionListener
     */
    private ActionListener startOnlineListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();

            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi Start offline
     * @return actionListener - obiekt klasy ActionListener
     */
    private ActionListener startOfflineListener() {

        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Menu(), newWindow.buttonsClickedBehaviour());
            }
        };

        return actionListener;
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi EXIT
     * @return actionListener - obiekt klasy ActionListener
     */
    private ActionListener exitButtonListener() {

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za wywołanie metody obiektu klasy NewWindow służącej do usunięcia wszystkich elemntów z obecnego JPanelu
     */
    private void cleanWindow(){
        newWindow.layoutMaker(this);
    }

}
