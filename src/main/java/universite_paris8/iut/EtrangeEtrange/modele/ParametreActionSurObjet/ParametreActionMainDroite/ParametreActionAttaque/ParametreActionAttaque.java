package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;



public abstract class ParametreActionAttaque extends ParametreAction
{

    public ParametreActionAttaque(EntiteOffensif origineAction)
    {
        super(origineAction);
    }

    public EntiteOffensif getOrigineAction()
    {
        return (EntiteOffensif) super.getOrigineAction();
    }

}
