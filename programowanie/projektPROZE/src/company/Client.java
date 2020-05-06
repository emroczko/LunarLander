package company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static Socket socket;
    static String Address;
    static int Port;
    static boolean online;
    static void Connect(String address, int port) throws UnknownHostException, IOException {
        socket = new Socket(address, port);
        Address = address;
        Port = port;
        System.out.println("Connected");

    }
    static String getProperty(String command) throws IOException {
        try {
            socket = new Socket(Address, Port);
        }
        catch(Exception e){e.printStackTrace();}
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println(command);
        InputStream in = socket.getInputStream();
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        return buf.readLine();
    }
    static String getConfigs() throws IOException{
        String configs = getProperty("GetConfigs");
        socket.close();
        return configs;
    }
    static String getLevel(int levelNumber) throws IOException{
        String levelConfigs = getProperty("GetLevel-" + levelNumber);
        socket.close();
        return levelConfigs;
    }
}
