package universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;


public abstract class ActionAttaquer extends ActionSurObjet
{
    public ActionAttaquer(EntiteOffensif origineAction)
    {
        super(origineAction);
    }

    public EntiteOffensif getOrigineAction()
    {
        return (EntiteOffensif) super.getOrigineAction();
    }

}
