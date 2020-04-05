package company;


import java.io.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;



public class PropertiesLoad {

    /** Okresla startowa szerokosc okna */
    static int xSize;
    /** Okresla startowa wysokosc okna */
    static int ySize;
    /** Okresla startowa wysokosc okna */
    static int LanderWidth;
    /** Okresla odpowiednia predkosc w plaszczyznie X */
    static int enginePowerVx;
    /** Okresla odpowiednia predkosc w plaszczyznie Y */
    static int enginePowerVy;
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


        static void loadProps() throws IOException {

            InputStream propertiesFile = new FileInputStream("Config.txt");
            Properties gameProps = new Properties();
            gameProps.load(propertiesFile);
            xSize = Integer.parseInt(gameProps.getProperty("xSize"));
            ySize = Integer.parseInt(gameProps.getProperty("ySize"));
            LanderWidth = Integer.parseInt(gameProps.getProperty("LanderWidth"));
            enginePowerVx = Integer.parseInt(gameProps.getProperty("enginePowerVx"));
            enginePowerVy = Integer.parseInt(gameProps.getProperty("enginePowerVy"));
            fuelAmount = Integer.parseInt(gameProps.getProperty("fuelAmount"));
            numberOfLives = Integer.parseInt(gameProps.getProperty("numberOfLives"));
            bonusPerSecond = Integer.parseInt(gameProps.getProperty("bonusPerSecond"));
            maxPointsAmount = Integer.parseInt(gameProps.getProperty("maxPointsAmount"));
            propertiesFile.close();

        }

}