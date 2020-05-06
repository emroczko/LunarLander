package company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
        while(true){
            try{
                Socket clientSocket = ss.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }
            catch(Exception e){e.printStackTrace();}
        }
    }
}
