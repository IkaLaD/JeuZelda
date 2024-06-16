package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Humain;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class HumainPNJ extends Humanoide  {
    public HumainPNJ(Monde monde, double x, double y, Direction direction, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Hitbox hitbox, Sac sac, Objet objetMainGauche, Objet objetMainDroite) {
        super(monde, x, y, direction, pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, hitbox, sac, objetMainGauche, objetMainDroite);
    }
}
