package company;

import javax.swing.*;
import java.awt.*;

public class LabelCustomizer {

    JLabel label = new JLabel();
    Color customizedColor;
    int customizedFont;

    public LabelCustomizer(Color color, int font){
        customizedColor = color;
        customizedFont = font;
        customizer(label);
    }

    public void customizer(JLabel label){
        label.setBackground(Color.black);
        label.setFont(Fonts.getFont(customizedFont));
        label.setForeground(customizedColor);
    }
}
