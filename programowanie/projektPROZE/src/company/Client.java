package company;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static Socket socket;
    static boolean offline = true;
    public static void Connect(String address, int port) throws UnknownHostException, IOException {
            socket = new Socket(address, port);
            System.out.println("Connected");
    }
}
