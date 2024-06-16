package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public class Fleche extends Projectile
{
    public Fleche() {super(0.08,new Hitbox(0.5,0.2));}

    @Override
    public double degatPhysique() {
        return 10;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }

    @Override
    public boolean peutSeDeplacer()
    {
        return !monde.estHorsMap(this);
    }

    @Override
    public String getNom() {
        return "Fleche";
    }
    @Override
    public String typeActeur(){
        return "fleche";
    }
    @Override
    public void seFaitPousser(Acteur acteur) {}
    @Override
    public int stackMax() {
        return 64;
    }
    @Override
    public void subitCollision(Acteur acteur) { enleveToutPv(); }
    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this);
        enleveToutPv();
    }

    @Override
    public void subitAttaque(Dommageable causeDegat) {}




    @Override
    public void unTour()
    {
        if(peutSeDeplacer())
            seDeplace(1);
        else {
            enleveToutPv();
        }

    }


    @Override
    public double durabilitee(){ return getPv();}

    @Override
    public int prixAchat() {
        return 0;
    }

}
