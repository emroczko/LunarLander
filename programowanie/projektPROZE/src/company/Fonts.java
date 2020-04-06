package company;

import java.awt.*;
import java.io.File;

public class Fonts {

    public Fonts(){

    }
    private static final Font SERIF_FONT = new Font("serif", Font.PLAIN, 24);

    public static Font getFont(int size) {
        Font font = null;
        try {
            String fName = "uni05_53.ttf";
            File fontFile = new File(fName);
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN,size);


        } catch (Exception ex) {
            font = SERIF_FONT;
        }
        return font;
    }
}
