package company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class RankingSaver {

    private static ArrayList<String> nick;
    private static ArrayList<Integer> points;
    private static int[] max_ranking_points;
    private static String[] max_ranking_names;

    public RankingSaver(){
        nick = new ArrayList<String>();
        points = new ArrayList<Integer>();
    }

    /**
     * Zapisuje podane wartosci nick i points do pliku "Ranking.txt" gdzie znajdują sie wszystkie wyniki graczy
     * @param nick = nick do zapisania
     * @param points = punkty do zapisania
     * @throws IOException
     */
    static void saveToFile(String nick, int points) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ranking.txt", true));
        writer.append(nick+"="+points+"\n");
        writer.close();
    }

    /**
     * Wczytuje wszystkie wyniki z pliku "Ranking.txt" i wybiera 5 najlepszych
     * @throws IOException
     */
    private void loadLocalRanking() throws IOException{
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
                        if(points.get(j)>max_ranking_points[i]){
                            max_ranking_points[i] = points.get(j);
                            max_ranking_names[i] = nick.get(j);
                            points.remove(j);
                            nick.remove(j);
                        }
                    }
                    else{
                        if(points.get(j)>max_ranking_points[i] && points.get(j)<=max_ranking_points[i-1]){
                            max_ranking_points[i] = points.get(j);
                            max_ranking_names[i] = nick.get(j);
                            points.remove(j);
                            nick.remove(j);
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