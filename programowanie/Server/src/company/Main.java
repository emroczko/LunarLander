package company;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        System.out.println("IP adress = " + InetAddress.getLocalHost());
        System.out.println("Port = " + PropertiesLoad.port);
        server.run();
    }
}
