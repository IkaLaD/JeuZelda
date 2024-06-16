package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;

public class Guerrier extends Joueur
{
    public Guerrier(Monde monde, double x, double y, Direction direction) {
        super(ConstantesPersonnages.GUERRIER_PV, ConstantesPersonnages.GUERRIER_ATTAQUE, ConstantesPersonnages.GUERRIER_DEFENSE, ConstantesPersonnages.GUERRIER_ATTAQUE_SPECIAL, ConstantesPersonnages.GUERRIER_DEFENSE_SEPCIAL, ConstantesPersonnages.GUERRIER_VITESSE, new Sac(), null, new Epee(), monde, x, y, direction, new Hitbox(0.50, 0.50));
        this.carquois = new Carquois();
        for(int i = 0 ; i < 200 ; i++){
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
            this.carquois.ajoutItem(new Fleche());
        }

    }


    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public void subitAttaque(Dommageable causeDegat) {

    }

}
