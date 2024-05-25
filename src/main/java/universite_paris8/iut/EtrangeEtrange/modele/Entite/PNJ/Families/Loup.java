package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class Loup extends Familie {

    public Loup(Joueur joueur, Monde monde, double x, double y, Direction direction, Hitbox hitbox, Aetoile aetoile) {
        super(joueur, 100, 15, 10, 10, 10, 0.3, monde, x, y, direction, hitbox, 1, aetoile); // Rayon de détection de 1
    }

    @Override
    public void attaque() {
        // Implémenter l'attaque spécifique du loup
    }


    @Override
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return 0;
    }

    @Override
    public void consommer() {
        // Implémenter la logique de consommation si nécessaire
    }
}
