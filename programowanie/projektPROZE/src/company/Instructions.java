package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructions extends JPanel{
    /** Obiekt klasy GridBagConstraintsMaker**/
    GridBagConstraintsMaker customGBC = new GridBagConstraintsMaker();
    /** Obiekt klasy NewWindow **/
    NewWindow newWindow = new NewWindow();

    JButton backButton = new JButton("Back");
    JLabel lost =new JLabel("INSTRUCTIONS");

    private ImageIcon MainMenuImage;

    /** Zmienna przechowująca nick gracza*/
    private String nick;
    /** Zmienne przechowująca wielkość poprzedniego okna*/
    private int a, b;

    Color aqua = new Color (51, 134, 175);
    Color citron = new Color (223, 234, 24);



    ButtonCustomizer customButton = new ButtonCustomizer(true, citron, 32);

    public Instructions(int xSize, int ySize){
        this.removeAll();
        repaint();
        revalidate();
        a = xSize;
        b = ySize;
        setPreferredSize(new Dimension(a,b));
        initializeVariables();
        this.setLayout(new GridBagLayout());

        backButton.addActionListener(returnToMainMenuButtonListener());
        customButton.customizer(backButton);
        this.add(backButton, customGBC.gbcCustomize(0,0,0,0,0,"LAST_LINE_END"));



    }

    /** metoda inicjalizująca obrazek tła za pomocą metody obiektu ImageFactory*/
    private void initializeVariables() {
        this.MainMenuImage = ImageFactory.createImage(Image.Instruction);
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
        newWindow.layoutMakerInstructions(this);
    }
}
