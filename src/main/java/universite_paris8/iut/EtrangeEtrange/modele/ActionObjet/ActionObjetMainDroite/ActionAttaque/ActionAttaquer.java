package universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;


public abstract class ActionAttaquer extends ActionSurObjet
{
    protected Arme arme;
    public ActionAttaquer(EntiteOffensif origineAction,Arme arme)
    {
        super(origineAction);
        this.arme = arme;
    }

    public EntiteOffensif getOrigineAction()
    {
        return (EntiteOffensif) super.getOrigineAction();
    }

    public void action()
    {
        attaque();
    }

    public abstract void attaque();
}
