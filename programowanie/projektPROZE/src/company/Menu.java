package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiedzialna za obsługę zdarzeń z pochodzących z graficznego interfejsu użytkownika
 */

public class Menu extends JPanel{
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

    public Menu() {
        this.removeAll();
        initializeLayout();
        initializeVariables();

        this.setLayout(new GridBagLayout());

        JButton startButton = new JButton("Start!");
        JButton rankingButton = new JButton("Best Scores");

        startButton.addActionListener(startButtonListener());
        rankingButton.addActionListener(rankingButtonListener());

        customButton.customizer(startButton);
        customButton.customizer(rankingButton);

        this.add(startButton, customGBC.gbcCustomize(0,1,0,0,0,"SOUTH"));
        this.add(rankingButton, customGBC.gbcCustomize(0,2,0,0,0, "SOUTH"));

    }
    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
        }

    /** metoda ustawiająca rozmiar okna za pomocą danych z pliku Config.txt*/
    private void initializeLayout() {
        setPreferredSize(new Dimension(PropertiesLoad.xSize, PropertiesLoad.ySize));
    }

    /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);

    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi START
     */
    private ActionListener startButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Name(getWidth(),getHeight()), newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }
    /**
     * Odpowiada za przypisanie akcji przyciskowi BEST SCORES
     */
    private ActionListener rankingButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Ranking(getWidth(),getHeight()), newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    /**
     * Odpowiada za wywołanie metody obiektu klasy NewWindow służącej do usunięcia wszystkich elemntów z obecnego JPanelu
     */
    private void cleanWindow(){
        newWindow.layoutMakerMenu(this);
    }

}
