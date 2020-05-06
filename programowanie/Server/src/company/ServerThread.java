package company;

import java.io.*;
import java.lang.Thread;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket clientSocket){
        this.socket = clientSocket;
    }
    public void run(){
        try{
            InputStream in = socket.getInputStream();
            BufferedReader br =  new BufferedReader(new InputStreamReader(in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e){e.printStackTrace();}
        while(true){

        }
    }
}
