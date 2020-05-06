package company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RankingSaver {

    static void saveToFile(String nick, int points) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Ranking.txt", true));
        writer.append(nick + "=" + points + "\n");
        writer.close();
    }
}
