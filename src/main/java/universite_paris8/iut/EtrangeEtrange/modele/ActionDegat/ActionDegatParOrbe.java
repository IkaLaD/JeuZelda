package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.ProjectileIntelligent.Orbe;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class ActionDegatParOrbe extends ActionDegatParEntite
{
    private Aetoile aetoile;
    private Orbe orbe;
    private int cycle;

    public ActionDegatParOrbe(EntiteOffensif origineAttaque, Dommageable orgineDegat) {
        super(origineAttaque, orgineDegat);
        this.orbe = (Orbe) orgineDegat;
        this.cycle = 0;
        this.aetoile = new Aetoile(origineAttaque.getMonde());
    }


    @Override
    public Position getPosition() {
        return orbe.getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        return orbe.getHitbox();
    }

    @Override
    public void miseAjour()
    {

    }
}
