package vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

public class VueTexte extends JPanel implements Observer {

    JButton config,alea,jouer,quit;
    JLabel deplacement, nbd;
    int deplactot;

    public VueTexte(){
        this.setLayout(new GridLayout(6,1));

        this.config = new JButton("Configurer");
        this.alea = new JButton("Aleatoire");
        this.jouer = new JButton("Jouer");
        this.quit = new JButton("Quitter");

        this.deplacement = new JLabel();
        this.deplacement.setHorizontalAlignment(SwingConstants.CENTER);

        this.nbd = new JLabel();
        this.nbd.setHorizontalAlignment(SwingConstants.CENTER);
        this.nbd.setFont(new Font("Arial",Font.BOLD,20));

        this.deplactot = 0;

        this.deplacement.setText("Nombre de deplacement:");
        this.nbd.setText(String.valueOf(this.deplactot));

        this.quit.addActionListener(e -> System.exit(0));

        this.add(this.config);
        this.add(this.alea);
        this.add(this.jouer);
        this.add(this.deplacement);
        this.add(this.nbd);
        this.add(this.quit);
    }

    public void ajouterDeplacement(){
        this.deplactot +=1;
        this.nbd.setText(String.valueOf(this.deplactot));
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setPreferredSize(new Dimension(200,500));
        VueTexte t = new VueTexte();
        f.add(t);
        f.pack();
        f.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO GET DEPLACEMENTS AND MAJ
        repaint();
    }
}
