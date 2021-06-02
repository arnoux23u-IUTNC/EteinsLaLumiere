package controlleurs;

import modeles.GrilleLampe;
import vues.VueGraphique;

import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe modelisant un controlleur de lampe
 * MVC : Controlleur, de type MouseInput
 *
 * @author arnoux23u
 * @author germonvi2u
 */
@SuppressWarnings("deprecation")
public class ControlleurLampe extends MouseInputAdapter {

    /**
     * Grille du controlleur attachee au MVC
     */
    private final GrilleLampe grille;

    /**
     * VueGraphique attachee au MVC
     */
    private final VueGraphique vg;

    /**
     * Constructeur public a deux parametres
     * Ajoute un listener
     *
     * @param gl Grille
     * @param vg VueGraphique
     */
    public ControlleurLampe(GrilleLampe gl, VueGraphique vg) {
        vg.addMouseListener(this);
        this.grille = gl;
        this.vg = vg;
    }

    /**
     * Methode ecrasee mousePressed
     * Declenchee par un clic de souris
     *
     * @param e Event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Point p = e.getPoint();
        int x = (int) p.getX() / (vg.getWidth() / 5);
        int y = (int) p.getY() / (vg.getHeight() / 5);
        if (x >= 0 && x < 5 && y >= 0 && y < 5) {
            switch (grille.getMode()) {
                case CONFIGURATION -> grille.switcher(x * 5 + y);
                case JOUER -> {
                    grille.switcher(x * 5 + y);
                    if (x > 0)
                        grille.switcher((x - 1) * 5 + y);
                    if (x < 4)
                        grille.switcher((x + 1) * 5 + y);
                    if (y > 0)
                        grille.switcher(x * 5 + (y - 1));
                    if (y < 4)
                        grille.switcher(x * 5 + (y + 1));
                    grille.setDeplacements(grille.getDeplacements() + 1);
                }
            }
        }
    }
}
