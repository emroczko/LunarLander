package company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class ServerScreen extends JPanel {
    private static DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<>();
    private JList list;
    private JScrollPane vertical;
    JLabel ip = new JLabel();
    JLabel port = new JLabel();

    LabelCustomizer customizedLabel = new LabelCustomizer(Color.white);
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();

    /**konstruktor klasy*/
    public ServerScreen() throws IOException {
        this.removeAll();
        initializeLayout();
        initializeVariables();
        this.setLayout(new GridBagLayout());

        ip.setText("IP adress = " + InetAddress.getLocalHost().getHostAddress());
        port.setText("Port = " + PropertiesLoad.port);

        Server server = new Server();
        server.run();
        customizedLabel.customizer(ip);
        customizedLabel.customizer(port);
        listModel.addElement("Messages");
        list = new JList(listModel);
        list.setPreferredSize(new Dimension(500, 400));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        list.setForeground(Color.white);
        list.setBackground(Color.black);
        list.setOpaque(true);

        vertical = new JScrollPane(list);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(ip, customGBC.gbcCustomize(0,0,0,0,0,"none"));
        this.add(port, customGBC.gbcCustomize(0,1,0,0,0,"none"));
        this.add(vertical, customGBC.gbcCustomize(0,2,0,1,0,"none"));
    }



    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.setBackground(Color.BLACK);
    }

    /** metoda ustawiająca rozmiar okna za pomocą danych z pliku Config.txt*/
    private void initializeLayout() {
        setPreferredSize(new Dimension(700,500));
    }
    public static void addMessage(String message){ listModel.addElement(message);
    }
}
