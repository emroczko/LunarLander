package company;

import javax.swing.*;
import java.awt.*;

public class ButtonCustomizer {

    JButton button = new JButton();
    Color customizedColor;
    int customizedFont;
    boolean isVisible;

    public ButtonCustomizer(boolean visible, Color color, int font){
        customizedColor = color;
        customizedFont = font;
        isVisible = visible;
        customizer(button);
    }

    public void customizer(JButton button){
        button.setFont(Fonts.getFont(customizedFont));
        button.setForeground(customizedColor);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setVisible(isVisible);
    }
}
