package modeles;

import ressources.Lampe;
import utils.Mode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * classe de modelisation
 */
public class GrilleLampe extends Observable {

    private ArrayList<Lampe> lampes;
    private Mode mode;
    private int deplacements;
    private boolean fini;

    public GrilleLampe() {
        this.mode = Mode.NORMAL;
        this.deplacements = 0;
        this.fini = true;
        creerGrille();
    }

    public void setDeplacements(int deplacements) {
        this.deplacements = deplacements;
    }

    private void creerGrille() {
        lampes = new ArrayList<Lampe>() {{
            for (int i = 0; i < 25; i++) {
                add(new Lampe());
            }
        }};
    }

    public void switcher(int i) {
        lampes.get(i).switcher();
        setChanged();
        notifyObservers();
    }

    public ArrayList<Lampe> getLampes() {
        return lampes;
    }

    public void shutdownAll() {
        for (Lampe l : lampes) {
            l.eteindre();
        }
        setChanged();
        notifyObservers();
    }

    public void regenerer(JPanel jp) {
        creerGrille();
        int nbAlea = Integer.parseInt(JOptionPane.showInputDialog(jp,"Nombre de lampes allumées ? (1-15)", 8));
        nbAlea = nbAlea < 1 ? 1 : Math.min(nbAlea, 15);
        while (nbAlea != 0){
            Lampe l = lampes.get(new Random().nextInt(lampes.size()));
            if(!l.isAllume()){
                l.allumer();
                nbAlea--;
            }
        }
        setChanged();
        notifyObservers();
    }

    public void changerMode(Mode newMode) {
        this.mode = newMode;
        setChanged();
        notifyObservers();
    }

    public Mode getMode() {
        return mode;
    }

    public void checkFini(){
        int nballum = 0;
        for (Lampe l : lampes){
            if (l.isAllume()){
                nballum++;
            }
        }
        this.fini = nballum == 0;
    }

    public boolean isFini() {
        return !this.fini;
    }

    public int getDeplacements() {
        return this.deplacements;
    }
}
