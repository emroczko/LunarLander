package company;

import javax.swing.*;
import java.awt.*;
/**
 * Klasa odpowiedzialna za usunięcie elementów danego okna w celu wczytania nowego
 */
public class NewWindow extends JPanel{
    public NewWindow(){
    }

    public GridBagConstraints buttonsClickedBehaviour(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }
    public void layoutMakerMenu(Menu menu){
        menu.removeAll();
        menu.repaint();
        menu.revalidate();
        menu.setLayout(new GridBagLayout());
    }
    public void layoutMakerName(Name name){
        name.removeAll();
        name.repaint();
        name.revalidate();
        name.setLayout(new GridBagLayout());
    }
    public void layoutMakerLevel(Level level){
        level.removeAll();
        level.repaint();
        level.revalidate();
        level.setLayout(new GridBagLayout());
    }
    public void layoutMakerLostGame(LostGame lostGame){
        lostGame.removeAll();
        lostGame.repaint();
        lostGame.revalidate();
        lostGame.setLayout(new GridBagLayout());
    }
    public void layoutMakerWonLevel(WonLevel wonLevel){
        wonLevel.removeAll();
        wonLevel.repaint();
        wonLevel.revalidate();
        wonLevel.setLayout(new GridBagLayout());
    }
    public void layoutMakerWonGame(WonGame wonGame){
        wonGame.removeAll();
        wonGame.repaint();
        wonGame.revalidate();
        wonGame.setLayout(new GridBagLayout());
    }
    public void layoutMakerIntroLevel(IntroLevel introLevel){
        introLevel.removeAll();
        introLevel.repaint();
        introLevel.revalidate();
        introLevel.setLayout(new GridBagLayout());
    }
    public void layoutMakerRanking(Ranking ranking){
        ranking.removeAll();
        ranking.repaint();
        ranking.revalidate();
        ranking.setLayout(new GridBagLayout());
    }
}

