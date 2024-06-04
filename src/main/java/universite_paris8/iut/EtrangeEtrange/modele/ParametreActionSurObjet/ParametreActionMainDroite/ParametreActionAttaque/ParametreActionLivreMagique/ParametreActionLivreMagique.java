package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;


public class ParametreActionLivreMagique extends ParametreActionAttaque
{
    private int numSort;
    public ParametreActionLivreMagique(EntiteOffensif origineAction,int numSort) {
        super(origineAction);
        this.numSort = numSort;
    }

    public int getNumSort() {
        return this.numSort;
    }
}
