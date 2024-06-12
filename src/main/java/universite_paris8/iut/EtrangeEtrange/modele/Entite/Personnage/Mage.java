package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Mage extends Joueur{
    public Mage(Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(ConstantesPersonnages.MAGE_PV, ConstantesPersonnages.MAGE_ATTAQUE, ConstantesPersonnages.MAGE_DEFENSE, ConstantesPersonnages.MAGE_ATTAQUE_SPECIAL, ConstantesPersonnages.MAGE_DEFENSE_SEPCIAL, ConstantesPersonnages.MAGE_VITESSE, sac, objetMainGauche, objetMainDroite, monde, x, y, direction, hitbox);
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public void unTour()
    {

    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public void subitDegat(Dommageable causeDegat) {

    }
}
