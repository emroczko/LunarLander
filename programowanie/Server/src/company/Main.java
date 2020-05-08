package company;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        EventQueue.invokeLater(() -> {
            try {
                new MainFrame();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
