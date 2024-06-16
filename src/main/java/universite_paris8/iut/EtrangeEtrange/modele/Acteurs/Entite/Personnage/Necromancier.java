package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Necromancier extends Joueur{
    public Necromancier(Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(ConstantesPersonnages.NECROMANCIER_PV, ConstantesPersonnages.NECROMANCIER_ATTAQUE, ConstantesPersonnages.NECROMANCIER_DEFENSE, ConstantesPersonnages.NECROMANCIER_ATTAQUE_SPECIAL, ConstantesPersonnages.NECROMANCIER_DEFENSE_SEPCIAL, ConstantesPersonnages.NECROMANCIER_VITESSE, sac, objetMainGauche, objetMainDroite, monde, x, y, direction, hitbox);
    }

    @Override
    public void unTour() {

    }

    @Override
    public void subitAttaque(Dommageable causeDegat) {

    }

    @Override
    public void dropApresMort() {

    }


}
