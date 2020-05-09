package company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RankingLoad {
    private static String stringToBeSent;

    /**
     * Wczytuje wszystkie wyniki z pliku "Ranking.txt"
     */
    private static void loadRanking() {

        try {
            Scanner scanner = new Scanner(new File("Ranking.txt"));
            while (scanner.hasNextLine()) {
                String[] temp = scanner.nextLine().split("=");
                stringToBeSent += (temp[0] + ";" + temp[1] + ";");
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String passRanking(){
        loadRanking();
        return stringToBeSent;
    }
}


