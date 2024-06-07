package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueDistance;


import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public class ParametreAttaqueArc extends ParametreActionAttaque
{
    private Projectile projectile;
    public ParametreAttaqueArc(EntiteOffensif origineAction, Projectile projectile)
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
