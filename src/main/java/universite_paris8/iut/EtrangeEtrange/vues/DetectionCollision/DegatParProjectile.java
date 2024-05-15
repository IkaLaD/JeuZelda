package universite_paris8.iut.EtrangeEtrange.vues.DetectionCollision;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class DegatParProjectile extends DegatParEntite
{

    public DegatParProjectile(EntiteOffensif origineAttaque, Projectile arme) {
        super(origineAttaque, arme);
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
}
