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

    private void creerGrille() {
        lampes = new ArrayList<>() {{
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
        String r = JOptionPane.showInputDialog(jp,"Nombre de lampes allumées ? (1-15)", 8);
        if(r!=null){
            int nbAlea = Integer.parseInt(r);
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
    }

    public void changerMode(Mode newMode) {
        this.mode = newMode;
        if(newMode == Mode.NORMAL){
            this.setDeplacements(0);
        }
        setChanged();
        notifyObservers();
    }

    public Mode getMode() {
        return mode;
    }

    public boolean checkFini(){
        int nballum = 0;
        for (Lampe l : lampes){
            if (l.isAllume()){
                nballum++;
            }
        }
        this.fini = nballum == 0;
        return this.fini;
    }

    public void setDeplacements(int deplacements) {
        this.deplacements = deplacements;
        setChanged();
        notifyObservers();
    }

    public int getDeplacements() {
        return deplacements;
    }
}
