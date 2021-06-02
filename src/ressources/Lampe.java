package ressources;

/**
 * classe de modelisation d'une lampe
 *
 * @author arnoux23u
 * @author germonvi2u
 */
public class Lampe {

    /**
     * booleen etat de la lampe
     */
    private boolean allume;

    /**
     * Constructeur public par defaut, mode eteint
     */
    public Lampe() {
        this.allume = false;
    }

    /**
     * Methode de test allumee
     *
     * @return booleen, a vrai si allume
     */
    public boolean isAllume() {
        return this.allume;
    }

    /**
     * Methode de switch
     * Inverse l'etat de la lampe
     */
    public void switcher() {
        this.allume = !this.allume;
    }

    /**
     * Methode eteindre
     * eteins la lampe
     */
    public void eteindre() {
        this.allume = false;
    }
}
