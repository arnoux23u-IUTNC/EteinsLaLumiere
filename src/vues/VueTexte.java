package vues;

import modeles.GrilleLampe;
import utils.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * classe VueTexte
 * MVC : Vue representant la partie texte
 *
 * @author arnoux23u
 * @author germonvi2u
 * @deprecated class using observer, which is deprecated
 */
public class VueTexte extends JPanel implements Observer {

    /**
     * Jbutton pour passer en mode de configuration
     */
    private final JButton config;

    /**
     * Jbutton pour passer en mode aleatoire
     */
    private final JButton aleatoire;

    /**
     * Jbutton pour passer en mode jouer
     */
    private final JButton jouer;

    /**
     * Jbutton pour revenir en mode normal
     */
    private final JButton quitter;

    /**
     * Jlabel nombre de deplacements
     */
    private final JLabel count;

    /**
     * Grille instanciee par l'observer
     */
    private GrilleLampe grille;

    /**
     * Constructeur public par defaut
     * Instancie les bouttons et les labels, cree un gestionnaire de placement, ajoute des listeners
     */
    public VueTexte() {

        this.setPreferredSize(new Dimension(300, 800));
        this.setLayout(new GridLayout(6, 1));

        config = new JButton("Configurer");
        aleatoire = new JButton("Aleatoire");
        jouer = new JButton("Jouer");
        quitter = new JButton("Quitter");
        jouer.setEnabled(false);
        quitter.setEnabled(false);

        JLabel deplacement = new JLabel();
        deplacement.setHorizontalAlignment(SwingConstants.CENTER);

        count = new JLabel();
        count.setHorizontalAlignment(SwingConstants.CENTER);
        count.setFont(new Font("Arial", Font.BOLD, 20));

        deplacement.setText("Nombre de deplacement:");
        count.setText("0");

        config.addActionListener(e -> grille.changerMode(Mode.CONFIGURATION));
        aleatoire.addActionListener(e -> {
            grille.changerMode(Mode.ALEATOIRE);
            grille.regenerer(this);
        });
        jouer.addActionListener(e -> grille.changerMode(Mode.JOUER));
        quitter.addActionListener(e -> {
            grille.changerMode(Mode.NORMAL);
            grille.shutdownAll();
        });

        this.add(config);
        this.add(aleatoire);
        this.add(jouer);
        this.add(deplacement);
        this.add(count);
        this.add(quitter);
    }

    /**
     * Methode ecrasse paintComponent
     * Redessine les valeurs et l'etat des bouttons
     *
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (grille.getMode()) {
            case CONFIGURATION -> {
                config.setEnabled(false);
                aleatoire.setEnabled(true);
                quitter.setEnabled(false);
                jouer.setEnabled(!grille.checkFini());
            }
            case JOUER -> {
                if (grille.checkFini()) {
                    grille.changerMode(Mode.FINI);
                    return;
                }
                config.setEnabled(false);
                aleatoire.setEnabled(false);
                quitter.setEnabled(true);
                jouer.setEnabled(false);
            }
            case NORMAL -> {
                config.setEnabled(true);
                aleatoire.setEnabled(true);
                quitter.setEnabled(false);
                jouer.setEnabled(false);
            }
            case ALEATOIRE -> {
                config.setEnabled(true);
                aleatoire.setEnabled(true);
                quitter.setEnabled(false);
                jouer.setEnabled(!grille.checkFini());
            }
        }
        int deplacements = grille.getDeplacements();
        if(grille.getMode().equals(Mode.FINI)){
            count.setForeground(Color.red);
            count.setText("GAGNÉ : "+deplacements);
        }else{
            count.setForeground(Color.black);
            count.setText(String.valueOf(deplacements));
        }

    }

    /**
     * Methode ecrasee Update
     * Recharge la grille et appelle repaint
     *
     * @param o   Observable, ici une grille
     * @param arg argument
     */
    @Override
    public void update(Observable o, Object arg) {
        grille = (GrilleLampe) o;
        repaint();
    }
}
