package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;

public class EpeeDeSoldat extends Epee
{
    public EpeeDeSoldat(Hitbox hitbox) {
        super(hitbox);
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
    public double delaieEntreCoup() {
        return 1;
    }

    @Override
    public String getNom() {
        return "Epée De Soldat";
    }


}
