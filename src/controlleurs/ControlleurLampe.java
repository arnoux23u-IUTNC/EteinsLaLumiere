package controlleurs;

import modeles.GrilleLampe;
import ressources.Lampe;
import vues.VueGraphique;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ControlleurLampe extends MouseInputAdapter {

    private final GrilleLampe grille;

    public ControlleurLampe(GrilleLampe gl, VueGraphique vg) {
        vg.addMouseListener(this);
        this.grille = gl;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Point p = e.getPoint();
        int x = (int) p.getX() / (VueGraphique.W / 5);
        int y = (int) p.getY() / (VueGraphique.H / 5);
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
