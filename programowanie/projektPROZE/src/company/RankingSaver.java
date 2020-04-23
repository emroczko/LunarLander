package company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;


public class RankingSaver {

    private static ArrayList<String> nick;
    private static ArrayList<Integer> points;
    private int[] max_ranking_points;
    private String[] max_ranking_names;

    public RankingSaver(){
        nick = new ArrayList<String>();
        points = new ArrayList<Integer>();
    }

    /***/
    static void saveToFile(String nick, int points) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ranking.txt", true));
        writer.append(nick+"="+points);
        writer.append("\n");
        writer.close();
    }
    /** */
    static void loadLocalRanking() throws IOException{
        try{
            Scanner scanner = new Scanner(new File("Ranking.txt"));
            while(scanner.hasNextLine()){
                String[] temp = scanner.nextLine().split("=");
                nick.add(temp[0]);
                points.add(Integer.parseInt(temp[1]));
            }
            int[] max_ranking_points = {0, 0, 0, 0, 0};
            String[] max_ranking_names = {"", "", "", ""};
            for(int i=0; i<max_ranking_points.length-1; i++){
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
    private String[][] bestScores(String nick, String points){
        try{
            loadLocalRanking();
        }
        catch(Exception e){e.printStackTrace();}
        String[][] arrStr = new String[5][2];
        for (int i = 0; i < arrStr.length; i++) {
            arrStr[i][0] = max_ranking_names[i];
            arrStr[i][i] = Integer.toString(max_ranking_points[i]);
        }
        return arrStr;

    }
}
