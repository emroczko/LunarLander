package company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za wczytywanie najlepszych wyników
 */
public class RankingLoader {

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
    public RankingLoader(){
        nick = new ArrayList<>();
        points = new ArrayList<>();
    }

    /**
     * Wczytuje wszystkie wyniki z pliku "Ranking.txt" i wybiera 5 najlepszych
     */
    private void loadLocalRanking(){
        try{
            Scanner scanner = new Scanner(new File("Ranking.txt"));
            while(scanner.hasNextLine()){
                String[] temp = scanner.nextLine().split("=");
                nick.add(temp[0]);
                points.add(Integer.parseInt(temp[1]));
            }
            max_ranking_points = new int[]{0, 0, 0, 0, 0};
            max_ranking_names = new String[]{"", "", "", "", ""};
            for(int i=0; i<max_ranking_points.length; i++){
                for(int j=0; j<points.size(); j++) {
                    if (i==0){
                        if(points.get(j)>this.max_ranking_points[i]){
                            this.max_ranking_points[i] = points.get(j);
                            this.max_ranking_names[i] = nick.get(j);
                            this.points.remove(j);
                            this.nick.remove(j);
                        }
                    }
                    else{
                        if(this.points.get(j)>this.max_ranking_points[i] && this.points.get(j)<=this.max_ranking_points[i-1]){
                            this.max_ranking_points[i] = points.get(j);
                            this.max_ranking_names[i] = nick.get(j);
                            this.points.remove(j);
                            this.nick.remove(j);
                        }
                    }
                }
            }
            scanner.close();
        }
        catch(Exception e){e.printStackTrace();}
    }

    /**
     * Tworzy tablicę 2 wymiarową wypełnioną wartościami wczytanymi przez funkcję loadLocalRanking
     * @return zwraca tablice 2 wymiarową
     */
    public String[][] bestScores(){
        try{
            loadLocalRanking();
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