package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns.Pattern;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class Boss extends Monstre {


    public Boss(Monde monde, double x, double y, Direction direction, double pv, double defense, double defenseSpecial, double vitesse, Hitbox hitbox, Pattern pattern) {
        super(monde, x, y, direction, pv, defense, defenseSpecial, vitesse, hitbox, pattern);
    }


}
