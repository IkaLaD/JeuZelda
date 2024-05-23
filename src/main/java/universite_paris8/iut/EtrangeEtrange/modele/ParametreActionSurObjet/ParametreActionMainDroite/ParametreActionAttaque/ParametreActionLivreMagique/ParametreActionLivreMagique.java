package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;

public class ParametreActionLivreMagique extends ParametreActionObjet
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
