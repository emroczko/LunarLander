package company;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiedzialna za utworzenie okna głównego i stworzenie w nim okna głównego menu
 */

public class MainMenu extends JFrame {


    public MainMenu() {
        initializeLayout();
    }

    /** metoda tworząca obiekt głównego menu, tytułuje okno, oraz ustawia kilka podstawowych własciowści gry:
     * możliwość zmiany rozmiaru, zamykanie aplikacji*/

    private void initializeLayout() {
        setTitle("Lunar Lander");
        setIconImage(ImageFactory.createImage(Image.Lander).getImage());
        add(new Menu());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

}

