package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueDistance.ActionAttaqueAvecArc;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.DegatParProjectile;

public class Arc extends Arme
{
    @Override
    public double degatPhysique() {
        return 0;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }

    @Override
    public double portee() {
        return 0;
    }

    @Override
    public double angle() {
        return 0;
    }

    @Override
    public double delaieEntreCoup() {
        return 0;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public int stackMax() {
        return 0;
    }



    public void tirerUneFleche(EntiteOffensif tireur,Fleche fleche)
    {
        tireur.getMonde().ajoutCauseDegat(new DegatParProjectile(tireur,fleche));
    }

    @Override
    public void utilise(ActionSurObjet action)
    {
        if (action instanceof ActionAttaqueAvecArc)
            action.action();
    }


}
