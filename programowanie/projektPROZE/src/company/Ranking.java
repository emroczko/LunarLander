package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ranking extends JPanel{

        private ImageIcon MainMenuImage;


            /** Zmienna przechowująca nick gracza*/
            private String nick;
            /** Zmienne przechowująca wielkość poprzedniego okna*/
            private int a, b;

        Color aqua = new Color (51, 134, 175);
        Color citron = new Color (223, 234, 24);


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

                JButton backButton = new JButton("Return to Main Menu");
                JLabel lost =new JLabel("RANKING");


                backButton.addActionListener(returnToMainMenuButtonListener());

                customButton.customizer(backButton);
                customLabel.customizer(lost);

                gbc.gridwidth = 3;
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.insets = new Insets(15, 15, 15, 15);
                this.add(lost, gbc);


                gbc.gridx = 0;
                gbc.gridy = 4;
                this.add(backButton, gbc);

                gbc.weightx = 1.0;
                gbc.weighty = 1.0;



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
                        add(new Menu(),buttonsClickedBehaviour());
                    }
                };
                return actionListener;
            }

            /**
             * Odpowiada za wyczyszczenie ekranu i umieszczenie nowego okna po naciśnięciu któregoś z przycisków w oknie Name
             */
            private GridBagConstraints buttonsClickedBehaviour(){
                removeAll();
                repaint();
                revalidate();
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.weightx = 1;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.BOTH;
                return gbc;
            }
        }


