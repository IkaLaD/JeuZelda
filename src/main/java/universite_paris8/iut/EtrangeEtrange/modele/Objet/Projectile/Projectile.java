package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public abstract class Projectile extends Acteur implements Dommageable,Objet
{
    public Projectile(double pv,double vitesse,Hitbox hitbox)
    {
        super(pv,vitesse,hitbox);
    }

    @Override
    public void unTour()
    {
        if(peutSeDeplacer())
            seDeplace(1);
        else
            enleveToutPv();
    }

    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this);
        enleveToutPv();
        System.out.println("tien - " + acteur.typeActeur());

    }
    @Override
    public void subitCollision(Acteur acteur) { enleveToutPv();System.out.println("aie "); }
    @Override
    public boolean peutSeDeplacer()
    {
        return !monde.estHorsMap(this);
    }
    @Override
    public void seFaitPousser(Acteur acteur) {/*NE FAIT RIEN*/}
    @Override
    public void subitAttaque(Dommageable causeDegat) {/*NE FAIT RIEN*/}

}
