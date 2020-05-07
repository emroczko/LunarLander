package company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RankingLoad {
    /** Lista przechowująca nicki graczy z pliku Ranking.txt*/
    private static ArrayList<String> nick;
    /**Lista przechowująca punkty graczy z pliku Ranking.txt*/
    private static ArrayList<Integer> points;
    /** Tablica z 5 najlepszymi wynikami*/
    private static int[] max_ranking_points;
    /** Tablica z 5 nickami graczy z najlepszymi wynikami*/
    private static String[] max_ranking_names;

    /**
     * konstruktor klasy inicjujacy zmienne nick i points
     */
    public RankingLoad(){
        nick = new ArrayList<>();
        points = new ArrayList<>();
    }

    /**
     * Wczytuje wszystkie wyniki z pliku "Ranking.txt"
     */
    private static void loadRanking(){
        try{
            Scanner scanner = new Scanner(new File("Ranking.txt"));
            while(scanner.hasNextLine()){
                String[] temp = scanner.nextLine().split("=");
                nick.add(temp[0]);
                points.add(Integer.parseInt(temp[1]));
            }
            parser();
            scanner.close();
        }
        catch(Exception e){e.printStackTrace();}
    }

    /**
     * Wybiera 5 najlepszych wyników
     */
    private static void parser(){
        max_ranking_points = new int[]{0, 0, 0, 0, 0};
        max_ranking_names = new String[]{"", "", "", "", ""};
        for(int i=0; i<max_ranking_points.length; i++){
            int max = 0;
            int index = 0;
            for(int j=0; j<points.size(); j++) {
                if (points.get(j)>=max)
                {
                    max =points.get(j);
                    index = j;
                }
            }
            if (points.size() != 0) {
                max_ranking_points[i] = points.get(index);
                max_ranking_names[i] = nick.get(index);
                nick.remove(index);
                points.remove(index);
            }
        }
    }
    /**
     * Tworzy tablicę 2 wymiarową wypełnioną wartościami wczytanymi przez funkcję loadLocalRanking
     * @return zwraca tablice 2 wymiarową
     */
    public static String[][] bestScores(){
        try{
            loadRanking();
        }
        catch(Exception e){e.printStackTrace();}
        String[][] arrStr = new String[5][2];
        for (int i = 0; i < max_ranking_names.length; i++) {
            arrStr[i][0] = max_ranking_names[i];
            arrStr[i][1] = Integer.toString(max_ranking_points[i]);
        }
        return arrStr;
    }
}
