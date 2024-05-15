package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class FlecheSimple extends Fleche
{
    public FlecheSimple(Monde monde, Position positionOrigine, Direction direction)
    {
        super(monde, positionOrigine, new Vitesse(1), direction);
    }

    @Override
    public double degatPhysique() {
        return 10;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }

    @Override
    public double delaieEntreCoup() {
        return 0;
    }

    @Override
    public String getNom() {
        return "Fleche";
    }
}
