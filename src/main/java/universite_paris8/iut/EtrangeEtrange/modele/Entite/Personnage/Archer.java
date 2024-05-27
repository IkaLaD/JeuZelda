package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

public class Archer extends Joueur
{
    public Archer(Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(ConstantesPersonnages.ARCHER_PV, ConstantesPersonnages.ARCHER_ATTAQUE, ConstantesPersonnages.ARCHER_DEFENSE, ConstantesPersonnages.ARCHER_ATTAQUE_SPECIAL, ConstantesPersonnages.ARCHER_DEFENSE_SEPCIAL, ConstantesPersonnages.ARCHER_VITESSE, sac, objetMainGauche, objetMainDroite, monde, x, y, direction, hitbox);
        this.carquois = new Carquois();
    }
}