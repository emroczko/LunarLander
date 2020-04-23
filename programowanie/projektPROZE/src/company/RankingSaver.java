package company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;


public class RankingSaver {

    private static String nick;
    private String[] nicks;
    private static String points;
    private int i = 0;

    /***/
    static void saveToFile(String nick, int points) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ranking.txt", true));
        writer.append(nick+"="+points);
        writer.append("\n");
        writer.close();
    }
    /** */
    static void loadLocalRanking() throws IOException{
        Path path = Paths.get("Ranking.txt");
        long lineCount = Files.lines(path).count();
        BufferedReader reader = new BufferedReader(new FileReader("Ranking.txt"));
        String line = reader.readLine();
        while (line != null) {
            nick = nick.substring(0, nick.indexOf('='));
            points = points.split("=", 2)[0];
            bestScores(nick, points);
            line = reader.readLine();
        }
        reader.close();
    }
    private static String[][] bestScores(String nick, String points){
        String[][] arrStr = new String[5][2];
        for (int i = 0; i < arrStr.length; i++) {
            for (int j = 0; j < arrStr[i].length; j++) {
                arrStr[i][j] = "Str" + j;
            }
        }
        return arrStr;
    }
}
