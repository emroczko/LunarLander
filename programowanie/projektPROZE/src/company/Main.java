package company;


import java.io.IOException;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                PropertiesLoad.loadProps();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new MainMenu();
        });

    }
}
