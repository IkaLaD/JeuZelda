package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class ActionDegatParProjectile extends ActionDegatParEntite
{

    public ActionDegatParProjectile(EntiteOffensif origineAttaque, Projectile projectile)
    {
        super(origineAttaque, projectile);
        projectile.setDirection(origineAttaque.getDirection());
        projectile.setPositionOrigine(origineAttaque.getPosition());


    }

    @Override
    public Position getPosition() {
        Projectile projectile = (Projectile) getOrgineAttaque();
        return projectile.getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        Projectile projectile = (Projectile) getOrgineAttaque();

        return projectile.getHitbox();
    }

    @Override
    public void miseAjour() {
        Projectile projectile = (Projectile) getOrgineAttaque();
        projectile.seDeplace();

        if (projectile.aToucherUneCible())
            getOrigineDegat().getMonde().enleveCauseDegat(this);
    }
}
