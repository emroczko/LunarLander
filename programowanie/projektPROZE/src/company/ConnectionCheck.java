package company;

import javax.swing.*;
import java.io.IOException;

public class ConnectionCheck {

    public static void detectServer() {
        if(Client.online) check();
    }
    private static void check(){
        String check = null;
        try{
            check = Client.checkConnected();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        if(!(check.equals("connected"))){
            Client.online = false;

        }
    }
}
