package company;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;


public class PropertiesLoad {

    /** Okresla startowa szerokosc okna */
    static int xSize;
    /** Okresla startowa wysokosc okna */
    static int ySize;
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


    /** Metoda wczytująca plik konfiguracyjny i obłsugująca parsowanie danych
     *
     * @throws IOException
     */
        static void loadProps() throws IOException {

            Properties gameProps = new Properties();
            gameProps.load(new FileInputStream("Config.txt"));
            xSize = Integer.parseInt(gameProps.getProperty("xSize"));
            ySize = Integer.parseInt(gameProps.getProperty("ySize"));
            enginePowerVx = Integer.parseInt(gameProps.getProperty("enginePowerVx"));
            enginePowerVy = Integer.parseInt(gameProps.getProperty("enginePowerVy"));
            fuelAmount = Integer.parseInt(gameProps.getProperty("fuelAmount"));
            numberOfLives = Integer.parseInt(gameProps.getProperty("numberOfLives"));
            bonusPerSecond = Integer.parseInt(gameProps.getProperty("bonusPerSecond"));
            maxPointsAmount = Integer.parseInt(gameProps.getProperty("maxPointsAmount"));

        }

}