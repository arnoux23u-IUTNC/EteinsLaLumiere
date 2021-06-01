package modeles;

import ressources.Lampe;

import java.util.ArrayList;
import java.util.Observable;

/**
 * classe de modelisation
 */
public class GrilleLampe extends Observable {

    private ArrayList<Lampe> lampes;

    public GrilleLampe(){
        lampes = new ArrayList<Lampe>(){{
            for(int i = 0 ; i < 25; i ++){
                add(new Lampe());
            }
        }};
    }

    public void switcher(int i, boolean b){
        lampes.get(i).switcher();
        setChanged();
        notifyObservers();
    }

    public ArrayList<Lampe> getLampes() {
        return lampes;
    }
}
