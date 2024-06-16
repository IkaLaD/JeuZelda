package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public abstract class Projectile extends Acteur implements Dommageable,Objet
{
    private Entite utilisateur;

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
        if (acteur != utilisateur)
        {
            acteur.subitAttaque(this);
            enleveToutPv();
        }
    }

    @Override
    public void subitCollision(Acteur acteur) {
        if (acteur != utilisateur)
            enleveToutPv();
    }

    @Override
    public boolean peutSeDeplacer() {return !monde.estHorsMap(this) && !monde.collisionMap(this);}
    public void setUtilisateur(Entite entite){this.utilisateur = entite;}
    @Override
    public void seFaitPousser(Acteur acteur) {}
    @Override
    public void subitAttaque(Dommageable causeDegat) {}

}