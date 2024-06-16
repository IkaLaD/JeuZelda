package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Bloc;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dropable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;


public class Bloc extends Acteur implements Dropable {
    public Bloc(Monde monde, double x, double y, Direction direction, double pv, double vitesse, Hitbox hitbox) { super(monde, x, y, direction, pv, vitesse, hitbox);}

    @Override
    public void seFaitPousser(Acteur acteur)
    {
        setDirection(acteur.getDirection());
        setVitesse(acteur.getVitesse());

        if (peutSeDeplacer())
            seDeplace(1);
    }

    @Override
    public void subitCollision(Acteur acteur) {acteur.causeCollision(this);}
    @Override
    public void subitAttaque(Dommageable causeDegat) {enleveToutPv();}
    @Override
    public String typeActeur() { return "bloc";}
    @Override
    public boolean estUnEnemie() { return false; }
    @Override
    public void drop() { monde.ajouterDropAuSol(new DropAuSol(new Arc(), 1, new Position(position.getX(), position.getY()))); }
    @Override
    public void unTour() {/*NE FAIT RIEN*/}
    @Override
    public void causeCollision(Acteur acteur) {/*NE FAIT RIEN*/}
}
