import java.awt.Button;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;

import javax.imageio.ImageIO;

class MainWindow extends Frame {
    /**
     *
     */
	BufferedImage img;
	//to to nie wiem co to ale jak się nie napisze to warninga daje xd
	private static final long serialVersionUID = 1L;
    MainWindow() 
    {   
    	//ścieżka do obrazka i załadowanie go do zmiennej img
    	String bg_path = "/MainMenu.png";
    	load_image(bg_path);
    	
    	//JLabel ktory ma w tle obraz i do którego dodaję później przyciski
        Container components_container = new JLabel(new ImageIcon(this.getClass().getResource(bg_path)));
        
        //Layouty to poczytaj sobie w necie jak to działa 
        components_container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Deklaracja przycisków z awt
        Button b_play = new Button("Play");
        Button b_best_scores = new Button("Best Scores");
        Button b_instructions = new Button("Instructions");
        
        //Zniana czcionki w texcie na przyciskach
        Font font = new Font("Arial", Font.PLAIN, 20);
        b_play.setFont(font);
        b_best_scores.setFont(font);
        b_instructions.setFont(font);
        
        //Ustawianie położenia przycisków w layoucie i dodawanie przycisków to Labela
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 15, 15, 15);
        components_container.add(b_play, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        components_container.add(b_best_scores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        components_container.add(b_instructions, gbc);
        
        //Podstawowe parametry okna (to dla setsize trzeba będzie wczytac z pliku config)
        this.setSize(700,500);
        this.add(components_container);
        this.setVisible(true);
        
    //To robi że jak klikniesz X to się zamknie okno
    this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent evt) {
            System.exit(0);
         }
    });
    }
    //ładuje obrazek wykorzystywany w paincie niżej
    public void load_image(String path) 
    {
	    try 
	    {
	    	img = ImageIO.read(this.getClass().getResource(path));
	    	System.out.println(img);
	    }
    catch(Exception e){e.printStackTrace();}
    }
    /**
     * Dzięki temu obraz w tle skaluje się do rozmiaru okna
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) {
        MainWindow win = new MainWindow();
    }

}