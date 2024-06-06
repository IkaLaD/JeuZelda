package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.PetitSac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;

public class Guerrier extends Joueur
{
    public Guerrier(Monde monde, double x, double y, Direction direction) {
        super(ConstantesPersonnages.GUERRIER_PV, ConstantesPersonnages.GUERRIER_ATTAQUE, ConstantesPersonnages.GUERRIER_DEFENSE, ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL, ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL, ConstantesPersonnages.GUERRIER_VITESSE, new PetitSac(), new Potion(), new LivreMagique(), monde, x, y, direction, new Hitbox(0.50, 0.50));
        carquois=new Carquois();
        for(int i = 0 ; i < 10 ; i++){
            carquois.ajoutItem(new FlecheSimple());
        }


    }
}
