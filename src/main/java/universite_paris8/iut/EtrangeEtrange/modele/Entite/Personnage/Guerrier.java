package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class Guerrier extends Joueur
{
    public Guerrier(Monde monde, double x, double y, Direction direction) {
        super(ConstantesPersonnages.GUERRIER_PV, ConstantesPersonnages.GUERRIER_ATTAQUE, ConstantesPersonnages.GUERRIER_DEFENSE, ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL, ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL, ConstantesPersonnages.GUERRIER_VITESSE, monde, x, y, direction, new Hitbox(ConstantesPersonnages.GUERRIER_HITBOX, ConstantesPersonnages.GUERRIER_HITBOX));
    }


    public void attaque() {

    }

    @Override
    protected double subitDegatPhysique(double attaque) {
        return attaque - getDefense().getDefenseActuelle();
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial) {
        return attaqueSpecial - getDefenseSpecial().getDefenseSpecialActuelle();
    }

}
