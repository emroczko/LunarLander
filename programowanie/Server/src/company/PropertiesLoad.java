package company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesLoad {
    static int port;

    /**
     * Wczytuje z pliku konfiguracyjnego numer portu
     * @throws IOException
     */
    static void loadPort() throws IOException {
        InputStream propertiesFile = new FileInputStream("Config.txt");
        Properties port_prop = new Properties();
        port_prop.load(propertiesFile);
        port = Integer.parseInt(port_prop.getProperty("port"));
        propertiesFile.close();
    }
    static String loadConfig() throws IOException{
        InputStream propertiesFile = new FileInputStream("Config.txt");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        String configs = "";
        for(int i=0; i<=10; i++){
            if(i<10){
                configs += properties.getProperty("prop" + i) + ";";
            }
            else{configs += properties.getProperty("prop" + i);}
        }
        return configs;
    }
    static String loadMapsConfigs(int levelnumber) throws IOException{
        InputStream propertiesFile = new FileInputStream("Maps.txt");
        Properties mapProps = new Properties();
        mapProps.load(propertiesFile);
        int[] xPoints = Arrays.stream(mapProps.getProperty("xpoints"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] yPoints = Arrays.stream(mapProps.getProperty("ypoints"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] xLanding = Arrays.stream(mapProps.getProperty("xlanding"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
        int[] yLanding = Arrays.stream(mapProps.getProperty("ylanding"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
        float mapGravity = Float.parseFloat(mapProps.getProperty("gravity"+levelnumber));
        return (Arrays.toString(xPoints) + ";" +  Arrays.toString(yPoints) + ";" + Arrays.toString(xLanding) + ";" +
                Arrays.toString(yLanding) + ";" + Float.toString(mapGravity)).replace("[","")
                .replace("]","").replace(",", "").trim();
    }
}
