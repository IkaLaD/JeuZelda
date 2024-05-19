package universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueMelee;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaquer;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.DegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;

public class ActionAttaquerAvecEpee extends ActionAttaquer
{
    public ActionAttaquerAvecEpee(EntiteOffensif origineAction, Epee arme) {
        super(origineAction,arme);
    }

    @Override
    public void attaque() {
        origineAction.getMonde().ajoutCauseDegat(new DegatParEpee((EntiteOffensif) origineAction, (Epee) arme));
    }
}
