package vues;

import modeles.GrilleLampe;
import ressources.Lampe;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * classe VueGraphique
 * MVC : Vue representant la partie graphique des lampes
 *
 * @author arnoux23u
 * @author germonvi2u
 * @deprecated class using Observer, which is deprecated
 */
public class VueGraphique extends JPanel implements Observer {

    /**
     * Grille instanciee par l'observer
     */
    private GrilleLampe grille;

    /**
     * Constante de taille de bordure d'ecran
     */
    public static final int BORDER = 10;

    /**
     * Constructeur public par defaut
     * Instancie une grille et appelle repaint
     */
    public VueGraphique() {
        setPreferredSize(new Dimension(800, 800));
        grille = new GrilleLampe();
        repaint();
    }

    /**
     * Methdode ecrasee paintComponent
     *
     * @param g graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int largeurLampe = (getWidth() - (2 * VueGraphique.BORDER)) / 5;
        int hauteurLampe = (getHeight() - (2 * VueGraphique.BORDER)) / 5;

        ArrayList<Lampe> lampes = grille.getLampes();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Lampe l = lampes.get(i * 5 + j);
                g.setColor(l.isAllume() ? new Color(162, 255, 0) : new Color(58, 110, 50));
                g.fillRect(BORDER + (i * largeurLampe), BORDER + (j * hauteurLampe), largeurLampe, hauteurLampe);
                g.setColor(Color.BLACK);
                g.drawRect(BORDER + (i * largeurLampe), BORDER + (j * hauteurLampe), largeurLampe, hauteurLampe);
            }
        }
    }

    /**
     * Methode ecrasee Update
     * Recharge la grille, verifie si la partie est finie et appelle repaint
     *
     * @param o   Observable, ici une grille
     * @param arg argument
     */
    @Override
    public void update(Observable o, Object arg) {
        grille = (GrilleLampe) o;
        grille.checkFini();
        repaint();
    }
}
