package company;

import java.awt.event.ActionEvent;

public class GameLoop implements actionlistener {

    private Level level;

    public GameLoop(Level level) {
        this.level = level;
    }

    @Override
    public void ActionPerformed(ActionEvent a) {
        this.level.doOneLoop();

    }
}
