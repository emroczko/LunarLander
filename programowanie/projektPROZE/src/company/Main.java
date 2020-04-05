package company;


import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        MainMenu win = new MainMenu();
        EventQueue.invokeLater(() -> {
            new MainMenu();
        });

    }
}
