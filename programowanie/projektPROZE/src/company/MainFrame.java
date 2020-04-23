package company;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Klasa odpowiedzialna za utworzenie okna głównego i stworzenie w nim okna głównego menu
 */

public class MainFrame extends JFrame {

    Menu menu = new Menu();

    public MainFrame() {

        initializeLayout();

    }
    

    /** metoda tworząca obiekt głównego menu, tytułuje okno, oraz ustawia kilka podstawowych własciowści gry:
     * możliwość zmiany rozmiaru, zamykanie aplikacji*/


    private void initializeLayout() {
        setTitle("Lunar Lander");
        setIconImage(ImageFactory.createImage(Image.Lander).getImage());
        add(menu);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);



    }


}

