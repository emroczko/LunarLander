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
}

