package vues;

import modeles.GrilleLampe;
import ressources.Lampe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class VueGraphique extends JPanel implements Observer {

    private GrilleLampe grille;
    private static final int BORDER = 10;
    public static int H;
    public static int W;

    public VueGraphique(){
        setPreferredSize(new Dimension(800,800));
        grille = new GrilleLampe();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        VueGraphique.H = getHeight()-(2*BORDER);
        VueGraphique.W = getWidth()-(2*BORDER);
        int largeurLampe = VueGraphique.W/5;
        int hauteurLampe = VueGraphique.H/5;

        ArrayList<Lampe> lampes = grille.getLampes();
        for (int i = 0; i < 5 ; i ++) {
            for (int j = 0; j < 5 ; j ++) {
                Lampe l = lampes.get(i*5+j);
                g.setColor(l.isAllume() ? new Color(162,255,0) : new Color(58,110,50));
                g.fillRect(BORDER+(i*largeurLampe),BORDER+(j*hauteurLampe),largeurLampe,hauteurLampe);
                g.setColor(Color.BLACK);
                g.drawRect(BORDER+(i*largeurLampe),BORDER+(j*hauteurLampe),largeurLampe,hauteurLampe);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        grille = (GrilleLampe) o;
        grille.checkFini();
        repaint();
    }

    public Point trouverPoint(Point p) {
        int x = (int) (p.getX()/(getWidth()/5));
        int y = (int) (p.getY()/(getHeight()/5));
        return new Point(x,y);
    }
}
