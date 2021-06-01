package utils;

import controlleurs.ControlleurLampe;
import modeles.GrilleLampe;
import vues.VueGraphique;
import vues.VueTexte;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GrilleLampe gl = new GrilleLampe();
        VueGraphique vg = new VueGraphique();
        VueTexte vt = new VueTexte();

        gl.addObserver(vg);
        gl.addObserver(vt);

        ControlleurLampe cl = new ControlleurLampe(gl, vg);

        JFrame f = new JFrame("Eteins la lumière");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(1050,800));
        f.add(vt,BorderLayout.WEST);
        f.add(vg,BorderLayout.CENTER);
        gl.shutdownAll();
        f.requestFocusInWindow();
        f.pack();
        f.setVisible(true);
    }
}
