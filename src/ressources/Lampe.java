package ressources;

/**
 * classe de modelisation d'une lampe
 */
public class Lampe {

    /**
     * etat de la lampe
     */
    private boolean allume;

    /**
     * Constructeur par defaut, mode eteint
     */
    public Lampe(){
        this.allume = false;
    }

    public void allumer(){
        this.allume = true;
    }

    public void eteindre(){
        this.allume = false;
    }

    public boolean isAllume(){
        return this.allume;
    }

}
