package company;

import java.net.Socket;

public class Client {
    private static Socket socket;
    private static boolean offline = true;
    public static void Connect(String address, int port){
        try{
            socket = new Socket(address, port);
            System.out.println("Connected");
        }
        catch(Exception e){e.printStackTrace();}
    }
}
