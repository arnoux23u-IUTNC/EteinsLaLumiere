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
        int x = (int) p.getX()/(VueGraphique.W/5);
        int y = (int) p.getY()/(VueGraphique.H/5);

        switch (grille.getMode()){
            case CONFIGURATION -> {
                grille.switcher(x*5+y);
            }
            case JOUER -> {
                grille.switcher(x*5+y);
                grille.switcher((x-1)*5+y);
                grille.switcher((x+1)*5+y);
                grille.switcher(x*5+(y-1));
                grille.switcher(x*5+(y+1));
            }
        }
    }
}
