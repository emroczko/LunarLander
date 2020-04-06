package company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEventListener extends KeyAdapter {
    private Level level;

    public GameEventListener(Level level){
        this.level = level;
    }

    @Override
    public void keyReleased(KeyEvent e){
        this.level.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e){
        this.level.keyPressed(e);
    }
}
