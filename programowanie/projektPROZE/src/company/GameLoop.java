package company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener{

    private Level level;

    public GameLoop(Level level) {
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        this.level.doOneLoop();

    }
}
