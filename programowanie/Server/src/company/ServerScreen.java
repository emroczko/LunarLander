package company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class ServerScreen extends JPanel {
    private static DefaultListModel listModel = new DefaultListModel();
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
        Server server = new Server();
        server.run();

        ip.setText("IP adress = " + InetAddress.getLocalHost());
        port.setText("Port = " + PropertiesLoad.port);


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

    /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle
     * @param g - obiekt klasy Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        update();
    }
    private void update(){
        revalidate();
        repaint();
    }
    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.setBackground(Color.BLACK);
    }

    /** metoda ustawiająca rozmiar okna za pomocą danych z pliku Config.txt*/
    private void initializeLayout() {
        setPreferredSize(new Dimension(700,500));
    }
    public static void addMessage(String message){
        listModel.addElement(message);
    }
}