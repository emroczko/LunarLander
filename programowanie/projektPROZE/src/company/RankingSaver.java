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
    private static int[] max_ranking_points;
    private static String[] max_ranking_names;

    public RankingSaver(){
        nick = new ArrayList<String>();
        points = new ArrayList<Integer>();
    }

    /***/
    static void saveToFile(String nick, int points) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ranking.txt", true));
        writer.append(nick+"="+points+"\n");
        writer.close();
    }
    /** */
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
<<<<<<< HEAD
            for(int i=0; i<max_ranking_points.length; i++){
=======
            for(int i=0; i<this.max_ranking_points.length; i++){
>>>>>>> 4cb79a5c8940fee2bbfb9cbd49fcf77b8648f7d5
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
    public String[][] bestScores(){
        try{
            loadLocalRanking();
        }
        catch(Exception e){e.printStackTrace();}
        String[][] arrStr = new String[5][2];
<<<<<<< HEAD
        for (int i = 0; i < max_ranking_names.length; i++) {
            arrStr[i][0] = max_ranking_names[i];
            arrStr[i][1] = Integer.toString(max_ranking_points[i]);
=======
        for (int i = 0; i < this.max_ranking_names.length; i++) {
            arrStr[i][0] = this.max_ranking_names[i];
            arrStr[i][1] = Integer.toString(this.max_ranking_points[i]);
>>>>>>> 4cb79a5c8940fee2bbfb9cbd49fcf77b8648f7d5
        }
        return arrStr;
    }
}