package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;

public class EpeeDeSoldat extends Arme {
    @Override
    public double degatPhysique() {
        return 20;
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
}
