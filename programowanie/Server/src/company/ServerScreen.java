package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

public class ServerScreen extends JPanel {
    private static DefaultListModel listModel = new DefaultListModel();
    private JList list;
    private JScrollPane vertical;
    JLabel ip = new JLabel();
    JLabel port = new JLabel();
    JButton turnOffButton = new JButton("Turn off server");
    JButton turnOnButton = new JButton("Turn on server");
    JButton resetConsoleButton = new JButton("Clear console");
    LabelCustomizer customizedLabel = new LabelCustomizer(Color.white);
    ButtonCustomizer customizedButton = new ButtonCustomizer();
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    Server server = new Server();

    /**konstruktor klasy*/
    public ServerScreen() throws IOException {
        this.removeAll();
        initializeLayout();
        initializeVariables();
        this.setLayout(new GridBagLayout());

<<<<<<< HEAD
=======
        server.run();

        resetConsoleButton.addActionListener(resetConsoleButtonListener());
        turnOnButton.addActionListener(turnOnButtonListener());
        turnOffButton.addActionListener(turnOffButtonListener());
>>>>>>> 98866061300d57c2e13e39da0737c4d61e9c37ce
        ip.setText("IP adress = " + InetAddress.getLocalHost().getHostAddress());
        port.setText("Port = " + PropertiesLoad.port);

        customizedButton.customizer(resetConsoleButton);
        customizedButton.customizer(turnOffButton);
        customizedButton.customizer(turnOnButton);
        customizedLabel.customizer(ip);
        customizedLabel.customizer(port);
        turnOnButton.setVisible(false);

        listModel.addElement("Messages:");
        list = new JList(listModel);
        list.setPreferredSize(new Dimension(500, 400));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(12);
        list.setFont(new Font("Menlo", Font.PLAIN, 14));
        list.setForeground(Color.white);
        list.setBackground(Color.black);
        list.setOpaque(true);

        vertical = new JScrollPane(list);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(ip, customGBC.gbcCustomize(0,0,0,0,0,"none"));
        this.add(port, customGBC.gbcCustomize(0,1,0,0,0,"none"));
<<<<<<< HEAD
        this.add(vertical, customGBC.gbcCustomize(0,2,0,1,0,"none"));
=======
        this.add(resetConsoleButton, customGBC.gbcCustomize(0,3,0,0,0,"none"));
        this.add(turnOffButton, customGBC.gbcCustomize(0,4,0,0,0,"none"));
        this.add(turnOnButton, customGBC.gbcCustomize(0,4,0,0,0,"none"));
        this.add(vertical, customGBC.gbcCustomize(0,2,0,0,0,"none"));

>>>>>>> 98866061300d57c2e13e39da0737c4d61e9c37ce
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
    public static void addMessage(String message){ listModel.addElement(message);
<<<<<<< HEAD
=======
    }
    /**
     * Odpowiada za przypisanie akcji przyciskowi reset console
     * @return actionListener - obiekt klasy ActionListener
     */
    private ActionListener resetConsoleButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                listModel.addElement("Messages:");
            }
        };
        return actionListener;
    }


    private ActionListener turnOffButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    turnOffServer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        return actionListener;
    }
    private ActionListener turnOnButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    turnOnServer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        return actionListener;
    }
    private void turnOffServer() throws IOException {
        server.turnOff = "yes";

        turnOffButton.setVisible(false);
        turnOnButton.setVisible(true);
        listModel.addElement("Server turned off");
    }
    private void turnOnServer() throws IOException {
        Server newServer = new Server();
        server.turnOff = "no";
        //server = newServer;
        newServer.run();
        turnOffButton.setVisible(true);
        turnOnButton.setVisible(false);
        listModel.addElement("Server turned on");
>>>>>>> 98866061300d57c2e13e39da0737c4d61e9c37ce
    }
}
