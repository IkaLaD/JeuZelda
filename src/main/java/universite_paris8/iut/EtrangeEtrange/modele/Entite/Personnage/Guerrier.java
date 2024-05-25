package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.PetitSac;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public class Guerrier extends Joueur
{
    public Guerrier(Monde monde, double x, double y, Direction direction) {
        super(ConstantesPersonnages.GUERRIER_PV, ConstantesPersonnages.GUERRIER_ATTAQUE, ConstantesPersonnages.GUERRIER_DEFENSE, ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL, ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL, ConstantesPersonnages.GUERRIER_VITESSE ,new PetitSac(), null, null,monde, x, y, direction ,new Hitbox(0.50,0.50));
    }

    @Override
    public void seDeplacerVersJoueur() {

    }
}
