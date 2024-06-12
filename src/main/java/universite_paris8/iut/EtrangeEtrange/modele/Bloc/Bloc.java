package universite_paris8.iut.EtrangeEtrange.modele.Bloc;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;


public class Bloc extends Acteur {
    public Bloc(Monde monde, double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) {
        super(monde, x, y, direction, pv, vitesse, hitbox);
    }

    @Override
    public void dropApresMort() {
        monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(position.getX(), position.getY()), monde.getJoueur()));
    }

    @Override
    public void unTour() {

    }
    @Override
    public void subitCollision(Acteur acteur) {
        if(acteur instanceof Entite) {
            setDirection(acteur.getDirection());
            setVitesse(acteur.getVitesse());
            if (peutSeDeplacer()) {
                seDeplace(1);
            }
        }
        if(acteur instanceof Dommageable){
            subitDegat(new Epee());
            dropApresMort();
        }
    }

    @Override
    public void subitDegat(Dommageable causeDegat) {
        statsPv.setPv(0);
    }

    @Override
    protected double subitDegatPhysique(double attaqueEntite, double degatArme) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecialEntite, double degatArme) {
        return 0;
    }


    @Override
    public String typeActeur() {
        return "bloc";
    }


}
