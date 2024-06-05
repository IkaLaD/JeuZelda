package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.ProjectileIntelligent;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Orbe extends Projectile
{
    private Aetoile aetoile;
    public Orbe() {
        super(new Hitbox(0.2,0.2));

    }

    public void suitJoueur(Entite entite)
    {

    }



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
    public String getNom() {
        return null;
    }

    @Override
    public int stackMax() {
        return 0;
    }
}
