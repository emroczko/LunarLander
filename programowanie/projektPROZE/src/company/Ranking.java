package company;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JPanel{

    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    JButton backButton = new JButton("Return to Main Menu");
    JLabel lost =new JLabel("RANKING");


    private ImageIcon MainMenuImage;


    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;

    Color aqua = new Color (51, 134, 175);
    Color citron = new Color (223, 234, 24);
    String[] columnNames = {"NICK", "SCORE"};
    Object[][] data = {
            {"ERYK", "120"},
            {"JULKA", "200"},
            {"",""},
            {"",""},
            {"",""},
    };

    JTable ranking = new JTable(data, columnNames);
    LabelCustomizer customLabel = new LabelCustomizer(aqua, 40);
    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);

    public Ranking(int xSize, int ySize){
        this.removeAll();
        repaint();
        revalidate();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));
        initializeVariables();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        backButton.addActionListener(returnToMainMenuButtonListener());

        customButton.customizer(backButton);
        customLabel.customizer(lost);

        ranking.setOpaque(true);
        ranking.setFont(Fonts.getFont(20));
        ranking.setBackground(Color.BLACK);
        ranking.setForeground(Color.WHITE);
        ranking.setGridColor(Color.BLACK);
        this.add(lost, customGBC.gbcCustomize(0,1,0,0,3,"none"));
        this.add(backButton, customGBC.gbcCustomize(0,3,0,0,3,"none"));
        this.add(ranking, customGBC.gbcCustomize(0,2,0,0,3,"none"));
        customGBC.gbcCustomize(0,0,1,1,0,"none");

    }

    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.MainMenu);
    }

    /** metoda przesłaniająca metodę paintComponent, w celu odpowiedniego skalowania obrazka w tle*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(MainMenuImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    /**
     * Odpowiada za przypisanie akcji przyciskowi BACK
     */
    private ActionListener returnToMainMenuButtonListener() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
                add(new Menu(),newWindow.buttonsClickedBehaviour());
            }
        };
        return actionListener;
    }

    private void cleanWindow(){
        newWindow.layoutMakerRanking(this);
    }
}


