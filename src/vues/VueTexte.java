package vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class VueTexte extends JPanel {

    JButton config,alea,jouer,quit;
    JLabel deplacement, nbd;

    public VueTexte(){

        this.setLayout(new GridLayout(6,1));

        this.config = new JButton("Configurer");
        this.alea = new JButton("Aleatoire");
        this.jouer = new JButton("Jouer");
        this.quit = new JButton("Quitter");

        this.deplacement = new JLabel();
        this.nbd = new JLabel();

        this.deplacement.setText("Nombre de deplacement:");
        this.nbd.setText("0");

        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };


    }


}
