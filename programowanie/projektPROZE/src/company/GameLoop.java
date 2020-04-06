package company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Klasa odpowiedzialna za obsługę zdarzeń gry
 */

public class GameLoop implements ActionListener{

    /** obiekt klasy Level*/
    private Level level;
    public GameLoop(Level level) {
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        this.level.doOneLoop();
    }
}
