package modeles;

import ressources.Lampe;
import utils.Mode;

import javax.swing.*;
import java.util.*;

/**
 * Classe de modelisation d'une grille de lampes
 * MVC : Modele sous forme d'observable
 *
 * @author arnoux23u
 * @author germonvi2u
 * @deprecated class using Observable, which is deprecated
 */
public class GrilleLampe extends Observable {

    /**
     * Liste des lampes de la grille
     */
    private ArrayList<Lampe> lampes;

    /**
     * Mode de jeu actuel
     */
    private Mode mode;

    /**
     * Nombre de deplacements en jeu
     */
    private int deplacements;

    /**
     * Etat de fin du jeu
     */
    private boolean fini;

    /**
     * Constructeur public par defaut
     * Regle le mode sur NORMAL, les deplacements a 0, simule une fin de partie et genere une grille
     */
    public GrilleLampe() {
        this.mode = Mode.NORMAL;
        this.deplacements = 0;
        this.fini = true;
        creerGrille();
    }

    /**
     * Methode de creation de grille
     * Remplit la liste de lampes avec des lampes eteintes
     */
    private void creerGrille() {
        lampes = new ArrayList<>() {{
            for (int i = 0; i < 25; i++) {
                add(new Lampe());
            }
        }};
    }

    /**
     * Methode de switch d'une lampe
     * Appelle la methode switch de la lampe en question
     * Appel explicite a l'observer
     *
     * @param i lampe a switcher
     */
    public void switcher(int i) {
        lampes.get(i).switcher();
        setChanged();
        notifyObservers();
    }

    /**
     * Methode getLampes
     *
     * @return liste de lampes
     */
    public ArrayList<Lampe> getLampes() {
        return lampes;
    }

    /**
     * Methode shutdownAll
     * Eteins toutes les lampes
     * Appel explicite a l'observer
     */
    public void shutdownAll() {
        for (Lampe l : lampes) {
            l.eteindre();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Methode de regeneration de grille utilisee dans un contexte aleatoire
     * Questionne l'utilisateur sur le nombre de lampes a allumer par defaut
     * Appel explicite a l'observer
     *
     * @param jp Jpanel parent
     */
    public void regenerer(JPanel jp) {
        creerGrille();
        String r = JOptionPane.showInputDialog(jp, "Nombre de lampes allumées ? (1-15)", 8);
        if (r != null) {
            int nbAlea = Integer.parseInt(r);
            nbAlea = nbAlea < 1 ? 1 : Math.min(nbAlea, 15);
            while (nbAlea != 0) {
                Lampe l = lampes.get(new Random().nextInt(lampes.size()));
                if (!l.isAllume()) {
                    l.switcher();
                    nbAlea--;
                }
            }
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Methode changerMode
     * Appel explicite a l'observer
     * Reinitialise les deplacements si le mode est normal
     *
     * @param newMode nouveau mode de jeu
     */
    public void changerMode(Mode newMode) {
        this.mode = newMode;
        if (newMode == Mode.NORMAL) {
            this.setDeplacements(0);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Methode getMode
     *
     * @return mode actuel du jeu
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Methode checkFini, verifie si la partie est terminee
     *
     * @return booleen, a vrai si la partie est finie
     */
    public boolean checkFini() {
        int nballum = 0;
        for (Lampe l : lampes) {
            if (l.isAllume()) {
                nballum++;
            }
        }
        this.fini = nballum == 0;
        return this.fini;
    }

    /**
     * Methode setDeplacements
     * Modifie le nombre de deplacements du jeu
     * Appel explicite a l'observer
     *
     * @param deplacements nouveau nombre de deplacements
     */
    public void setDeplacements(int deplacements) {
        this.deplacements = deplacements;
        setChanged();
        notifyObservers();
    }

    /**
     * Methode getdeplacements
     *
     * @return nombre de deplacements du jeu
     */
    public int getDeplacements() {
        return deplacements;
    }
}
