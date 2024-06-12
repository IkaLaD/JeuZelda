package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;

public class Archer extends Joueur
{
    public Archer(Monde monde, double x, double y, Direction direction) {
        super(ConstantesPersonnages.ARCHER_PV, ConstantesPersonnages.ARCHER_ATTAQUE, ConstantesPersonnages.ARCHER_DEFENSE, ConstantesPersonnages.ARCHER_ATTAQUE_SPECIAL, ConstantesPersonnages.ARCHER_DEFENSE_SEPCIAL, ConstantesPersonnages.ARCHER_VITESSE, null, null, null, monde, x, y, direction, new Hitbox(ConstantesPersonnages.GUERRIER_HITBOX,ConstantesPersonnages.GUERRIER_HITBOX));
        this.carquois = new Carquois();
    }

    @Override
    public void unTour() {

    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public void subitDegat(Dommageable causeDegat) {

    }
}