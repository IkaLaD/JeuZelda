package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public abstract class Fleche extends Projectile
{
    public Fleche() {super(1,new Hitbox(0.5,0.2));}

    @Override
    public double portee() {
        return 0;
    }
    @Override
    public double angle() {
        return 0;
    }
    @Override
    public int stackMax() {
        return 6;
    }
    @Override
    public void subitCollision(Acteur acteur) {enleveToutPv();}

    @Override
    public String typeActeur(){ return "Fleche"; }


    @Override
    public void unTour()
    {
        if(peutSeDeplacer())
            seDeplace(1);
        else
            enleveToutPv();

    }

}
