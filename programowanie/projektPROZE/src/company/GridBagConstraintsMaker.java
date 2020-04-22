package company;

import java.awt.*;

/**
 * Klasa odpowiedzialna za graficzne ustawianie przycisków
 */

public class GridBagConstraintsMaker {

    /** Obiekt klasy GridBagConstraints**/
    GridBagConstraints gbc = new GridBagConstraints();

    public GridBagConstraintsMaker(){

    };

    public GridBagConstraints gbcCustomize(int gridX, int gridY, double weightX, double weightY, int gridWidth, String whichDirection){
        gbc.gridwidth = gridWidth;
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.insets = new Insets(15, 15, 15, 15);
        anchorMaker(whichDirection, gbc);
        return gbc;
    }
    /**
     * Metoda ustawiająca metode anchor na tę zadaną przez użytkownika
     */
    private void anchorMaker(String whichDirection, GridBagConstraints gbc){
        switch(whichDirection){
            case "SOUTH": gbc.anchor = GridBagConstraints.SOUTH;
                break;
            case "NORTH": gbc.anchor = GridBagConstraints.NORTH;
                break;
            case "FIRST_LINE_END": gbc.anchor = GridBagConstraints.FIRST_LINE_END;
                break;
            case "FIRST_LINE_START": gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                break;
            case "NORTHWEST": gbc.anchor = GridBagConstraints.NORTHWEST;
                break;
            case "none": break;
        }

    }
}
