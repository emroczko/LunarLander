package company;

public class GameLoop implements actionlistener {

    private GamePanel gamePanel;

    public gameLoop(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Overrride
    public void actionperformed(ActionEvent a) {
        this.gamePanel.doOneloop();

    }
}
