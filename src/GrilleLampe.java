import ressources.Lampe;

import java.util.ArrayList;

/**
 * classe de modelisation
 */
public class GrilleLampe {

    private ArrayList<Lampe> lampes;

    public GrilleLampe(){
        lampes = new ArrayList<Lampe>(){{
            for(int i = 0 ; i < 25; i ++){
                add(new Lampe());
            }
        }};
    }

}
