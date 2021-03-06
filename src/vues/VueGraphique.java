package vues;

import modeles.GrilleLampe;
import ressources.Lampe;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
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
     * Couleur etat allume
     */
    public static final Color allume = new Color(162, 255, 0);

    /**
     * Couleur etat eteint
     */
    public static final Color eteint = new Color(58, 110, 50);

    /**
     * Couleur de fonc
     */
    public static final Color bgColor = new Color(20, 20, 20);

    /**
     * Constructeur public par defaut
     * Instancie une grille et appelle repaint
     */
    public VueGraphique() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(bgColor);
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
        Graphics2D graphics2D = (Graphics2D) g;
        int largeurLampe = (getWidth() - (2 * VueGraphique.BORDER)) / 5;
        int hauteurLampe = (getHeight() - (2 * VueGraphique.BORDER)) / 5;

        ArrayList<Lampe> lampes = grille.getLampes();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Lampe l = lampes.get(i * 5 + j);
                graphics2D.setColor(l.isAllume() ? allume : eteint);
                RoundRectangle2D rd = new RoundRectangle2D.Float(BORDER + (i * largeurLampe), BORDER + (j * hauteurLampe), largeurLampe, hauteurLampe, 40, 40);
                graphics2D.fill(rd);
                graphics2D.setColor(bgColor);
                graphics2D.draw(rd);
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
