package company;


import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Klasa odpowiedzialna za wczytywanie danych z plików i serwera (na razie tylko pliki, docelowo również z serwera)
 */

public class PropertiesLoad {

    /** Okresla startowa szerokosc okna */
    static int xSize;
    /** Okresla startowa wysokosc okna */
    static int ySize;
    /** Okresla startowa wysokosc okna */
    static int LanderWidth;
    /** Okresla odpowiednia predkosc w plaszczyznie X */
    static float enginePowerVx;
    /** Okresla odpowiednia predkosc w plaszczyznie Y */
    static float enginePowerVy;
    /** Okresla poczatkowa ilosc paliwa */
    static int fuelAmount;
    /** Okresla ilosc statkow */
    static int numberOfLives;
    /** Okresla ilosc punktow przyznawanych za pozostale sekundy */
    static int bonusPerSecond;
    /** Okersla punkt startowy w ktorym pojawia sie gracz */
    static int startPoint;
    /** Okersla punkt startowy w ktorym pojawia sie gracz */
    static int maxPointsAmount;
    /** tablica przechowujaca x'owe wspolrzedne wierzcholkow wielokata bedacych powierzchnia ksiezyca */
    static int[] xPoints;
    /** tabblica przechowujaca y'owe wspolrzedne wierzcholkow wielokata bedacych powierzchnia ksiezcya */
    static int[] yPoints;
    /** tablica przechowujaca x'owe wspolrzedne wierzcholkow wielokata bedacego ladowiskiem */
    static int[] xLanding;
    /** tablica przechowujaca y'owe wspolrzedne wierzcholkow wielokata bedacego ladowiskiem */
    static int[] yLanding;
    /** Określa przyśpieszenie grawitacyjne na danej mapie*/
    static float mapGravity;

        /** Metoda wczytuje dane z lokalnego pliku i zapisuje do odpowiednich pól w klasie */
        static void loadProps() throws IOException {

            InputStream propertiesFile = new FileInputStream("Config.txt");
            Properties gameProps = new Properties();
            gameProps.load(propertiesFile);
            xSize = Integer.parseInt(gameProps.getProperty("xSize"));
            ySize = Integer.parseInt(gameProps.getProperty("ySize"));
            LanderWidth = Integer.parseInt(gameProps.getProperty("LanderWidth"));
            enginePowerVx = Float.parseFloat(gameProps.getProperty("enginePowerVx"));
            enginePowerVy = Float.parseFloat(gameProps.getProperty("enginePowerVy"));
            fuelAmount = Integer.parseInt(gameProps.getProperty("fuelAmount"));
            numberOfLives = Integer.parseInt(gameProps.getProperty("numberOfLives"));
            bonusPerSecond = Integer.parseInt(gameProps.getProperty("bonusPerSecond"));
            maxPointsAmount = Integer.parseInt(gameProps.getProperty("maxPointsAmount"));
            propertiesFile.close();
        }
        /** metoda wczytująca współrzędne ukształtowania planet (Hit Box) i jej współczynnik grawitacji */
        static void loadMapsConfigs(int levelnumber) throws IOException{
            InputStream propertiesFile_maps = new FileInputStream("Maps.txt");
            Properties mapProps = new Properties();
            mapProps.load(propertiesFile_maps);
            xPoints = Arrays.stream(mapProps.getProperty("xpoints"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
            yPoints = Arrays.stream(mapProps.getProperty("ypoints"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
            xLanding = Arrays.stream(mapProps.getProperty("xlanding"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
            yLanding = Arrays.stream(mapProps.getProperty("ylanding"+levelnumber).split("-")).mapToInt(Integer::parseInt).toArray();
            mapGravity = Float.parseFloat(mapProps.getProperty("gravity"));
        }


}