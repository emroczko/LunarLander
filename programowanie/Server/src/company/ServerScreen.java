package company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class ServerScreen extends JPanel {
    JLabel ip = new JLabel();
    JLabel port = new JLabel();

    /**konstruktor klasy*/
    public ServerScreen() throws IOException {
        this.removeAll();
        initializeLayout();
        initializeVariables();
        this.setLayout(new GridBagLayout());

        ip.setText("IP adress = " + InetAddress.getLocalHost());
        port.setText("Port = " + PropertiesLoad.port);


        this.add(ip);
        this.add(port);



    }
    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.setBackground(Color.BLACK);
    }

    /** metoda ustawiająca rozmiar okna za pomocą danych z pliku Config.txt*/
    private void initializeLayout() {
        setPreferredSize(new Dimension(700,500));
    }



}
