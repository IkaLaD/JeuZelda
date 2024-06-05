package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class ActionDegatParProjectile extends ActionDegatParEntite
{
    private Projectile projectile;
    public ActionDegatParProjectile(EntiteOffensif origineAttaque, Projectile projectile)
    {
        super(origineAttaque, projectile);
        this.projectile = projectile;
    }

    @Override
    public Position getPosition() {
        return this.projectile.getPosition();
    }
    @Override
    public Hitbox getHitbox() {return projectile.getHitbox();}
    @Override
    public void miseAjour()
    {
        projectile.seDeplace();

        if (projectile.aToucherUneCible())
            getOrigineDegat().getMonde().enleveActionDegat(this);
    }


}
