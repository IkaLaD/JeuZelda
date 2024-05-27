package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueDistance;


import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public class ParametreActionAttaqueArc extends ParametreActionAttaque
{
    private Projectile projectile;
    public ParametreActionAttaqueArc(EntiteOffensif origineAction,Projectile projectile)
    {
        super(origineAction);
        this.projectile = projectile;
    }

    public Projectile getProjectile()
    {
        return this.projectile;
    }

    public void setProjectile(Projectile projectile)
    {
        this.projectile = projectile;
    }
}
