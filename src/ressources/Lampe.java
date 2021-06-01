package ressources;

import javax.swing.*;
import java.awt.*;

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
    public Lampe() {
        this.allume = false;
    }

    public boolean isAllume() {
        return this.allume;
    }

    public void switcher() {
        this.allume = !this.allume;
    }

    public void allumer(){
        this.allume = true;
    }

    public void eteindre() {
        this.allume = false;
    }
}
