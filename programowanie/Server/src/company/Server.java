package company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    /** Zmienna przechowująca port serwera*/
    int port;
    /**Konstruktor serwera przydzielający numer portu pobrany z pliku konfiguracyjnego*/
    public Server() throws IOException {
        PropertiesLoad.loadPort();
        port = PropertiesLoad.port;
    }

    /**
     *
     * @throws IOException
     */
    public void run() throws IOException{
        ServerSocket ss = new ServerSocket(port);

        Timer serverTimer = new Timer(true);
        serverTimer.scheduleAtFixedRate(new TimerTask() {    public void run() {
        while(true){
            try{
                Socket clientSocket = ss.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String fromClient = in.readLine();
                if (fromClient != null) {
                    System.out.println("From client: " + fromClient);
                    String serverRespond = ServerCommands.serverAction(fromClient);
                    out.println(serverRespond);
                    out.flush();
                    System.out.println("Server respond: " + serverRespond);
                    break;
                }
            }
            catch(Exception e){e.printStackTrace();}
        }
            }
        }, 0, 10);
    }
}
