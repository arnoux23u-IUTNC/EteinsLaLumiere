package utils;

import controlleurs.ControlleurLampe;
import modeles.GrilleLampe;
import vues.*;

import javax.swing.*;
import java.awt.*;

/**
 * Classe main
 * Lance un jeu
 *
 * @author arnoux23u
 * @author germonvi2u
 */
@SuppressWarnings("deprecation")
public class Main {

    /**
     * Methode main, s'occupe de creer un jeu
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        //Instanciation d'une grille et des deux vues
        GrilleLampe gl = new GrilleLampe();
        VueGraphique vg = new VueGraphique();
        VueTexte vt = new VueTexte();

        //Ajour des observers
        gl.addObserver(vg);
        gl.addObserver(vt);

        //Creation d'un controlleur
        new ControlleurLampe(gl, vg);

        //Creation de la fenetre
        JFrame f = new JFrame("Eteins la lumière - ARNOUX | GERMONVILLE-BELLET");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setIconImage(new ImageIcon("files/icon.jpg").getImage());
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(1100, 800));
        f.add(vt, BorderLayout.WEST);
        f.add(vg, BorderLayout.CENTER);
        gl.shutdownAll();   //Extinction des lampes par defaut
        f.requestFocusInWindow();
        f.pack();
        f.setVisible(true);
    }
}
