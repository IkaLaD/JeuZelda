package universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueDistance;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaquer;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.ArmeTirable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public class ActionAttaqueAvecArc extends ActionAttaquer
{
    private Projectile projectile;
    public ActionAttaqueAvecArc(EntiteOffensif origineAction, Projectile projectile) {
        super(origineAction);
        this.projectile = projectile;
    }

    public Projectile getProjectile()
    {
        return this.projectile;
    }

}
