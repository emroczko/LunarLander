package company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoad {
    static int port;
    static void loadPort() throws IOException {
        InputStream propertiesFile = new FileInputStream("Config.txt");
        Properties port_prop = new Properties();
        port_prop.load(propertiesFile);
        port = Integer.parseInt(port_prop.getProperty("port"));
        propertiesFile.close();
    }
}
