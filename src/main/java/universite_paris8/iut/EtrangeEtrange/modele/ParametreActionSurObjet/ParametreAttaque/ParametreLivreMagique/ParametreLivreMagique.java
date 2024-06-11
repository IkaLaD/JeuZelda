package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreActionAttaque;


public class ParametreLivreMagique extends ParametreActionAttaque
{
    private int numSort;
    public ParametreLivreMagique(EntiteOffensif origineAction, int numSort) {
        super(origineAction);
        this.numSort = numSort;
    }

    public int getNumSort() {
        return this.numSort;
    }
}
