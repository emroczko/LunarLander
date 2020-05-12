package company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Server {
    /**
     * Zmienna przechowująca port serwera
     */
    int port;
   String turnOff = "no";
    ServerSocket ss;
    /** Obiekt klasy ScheduledExecutorService **/
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    /**
     * Konstruktor serwera przydzielający numer portu pobrany z pliku konfiguracyjnego
     */
    public Server() throws IOException {
        PropertiesLoad.loadPort();
        port = PropertiesLoad.port;
       //
    }

    /**
     * @throws IOException
     */
    public void run() throws IOException {

        ss = new ServerSocket(port);
        ss.setReuseAddress(true);
        Runnable timeOn = () -> {
            try {
                if(turnOff.equals("no")) {

                    Socket clientSocket = ss.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String fromClient = in.readLine();
                    if (fromClient != null) messagesFromClient(out, fromClient);
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
        
        executor.scheduleAtFixedRate(timeOn, 1, 100, MILLISECONDS);

    }
    private void messagesFromClient(PrintWriter out, String fromClient) throws IOException {

        //ServerScreen.addMessage("From client: " + fromClient);
        System.out.println("From client: " + fromClient);
        String serverRespond = ServerCommands.serverAction(fromClient);
        out.println(serverRespond);
        out.flush();
        //ServerScreen.addMessage("Server respond: " + serverRespond);
        System.out.println("Server respond: " + serverRespond);
    }
    private void newExecutor(){
        ScheduledExecutorService newExecutor = Executors.newScheduledThreadPool(1);
        executor = newExecutor;
    }
    public void closeServer(ServerSocket ss) throws IOException {
        ServerSocket temp = new ServerSocket();
        try {
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        ss = temp;
        newExecutor();
    }
}
