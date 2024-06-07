package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class EpeeDeSoldat extends Epee
{

    public EpeeDeSoldat(Monde monde, double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) {
        super(monde, x, y, direction, pv, vitesse, hitbox);
    }

    @Override
    public double degatPhysique() {
        return 2;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }

    @Override
    public double portee() {
        return 0.8;
    }

    @Override
    public double angle() {
        return 180;
    }

    @Override
    public long delaie() {
        return 1;
    }



    @Override
    public void cooldown() {

    }

    @Override
    public String getNom() {
        return "Epée De Soldat";
    }


    @Override
    public void unTour() {

    }

    @Override
    public void subitCollision(Acteur acteur) {

    }
}
