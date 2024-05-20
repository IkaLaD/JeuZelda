package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;


public abstract class ParametreActionAttaque extends ParametreActionObjet
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
