package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Orbe extends Projectile
{
    public Orbe() {
        super(1,1,new Hitbox(0.2,0.2));
    }



    @Override
    public String typeActeur() {
        return null;
    }

    @Override
    public void seFaitPousser(Acteur acteur) {

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
    public String getNom() {
        return "orbe";
    }

    @Override
    public int stackMax() {
        return 0;
    }

    @Override
    public double durabilitee() {
        return 0;
    }

    @Override
    public int prixAchat() {
        return 0;
    }


}
