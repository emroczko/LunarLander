package company;

public class GameLoop implements actionlistener {

    private GamePanel gamePanel;

    public GameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Overrride
    public void actionperformed(ActionEvent a) {
        this.gamePanel.doOneloop();

    }
}
