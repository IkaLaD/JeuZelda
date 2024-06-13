package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.SeDeplacerVersJoueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import java.util.ArrayList;

public class Squelette extends EntiteOffensif implements PNJ, SeDeplacerVersJoueur
{
    private Joueur joueur;
    private Aetoile aetoile;
    private long lastPathCalculationTime;

    public Squelette(Monde monde, double x, double y, Direction direction, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Hitbox hitbox, Aetoile aetoile, Joueur joueur) {
        super(monde, x, y, direction, pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, hitbox);
        this.aetoile = aetoile;
        this.joueur = joueur;
    }


    @Override
    public void action() {
        seDeplacerVersJoueur(joueur.getPosition());
        attaque(new Epee());
    }

    @Override
    public void subitDegat(Dommageable causeDegat) {

    }

    @Override
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return (degat * forceEntite) / (getDefense() - (degat/6));
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return (attaqueSpecial * forceEntite) / (getDefense() - (attaqueSpecial/6));
    }


    @Override
    public void seDeplacerVersJoueur(Position joueurPosition) {
        if (aetoile == null) {
            System.out.println("Aetoile non configuré.");
            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPathCalculationTime >= 3000 || aetoile.getChemin().isEmpty()) {
            aetoile.trouverChemin(getPosition(), joueurPosition);
            lastPathCalculationTime = currentTime;
            if (aetoile.getChemin().isEmpty()) {
                System.out.println("Aucun chemin trouvé pour atteindre le joueur.");
                return;
            }
        }

        // Obtenir la prochaine position dans le chemin
        Position prochainePosition = aetoile.getChemin().get(0);

        // Calculer la direction vers la prochaine position
        double deltaX = prochainePosition.getX() - getPosition().getX();
        double deltaY = prochainePosition.getY() - getPosition().getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
        } else {
            setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);
        }

        // Déplacer l'entité si elle peut se déplacer
        if (peutSeDeplacer()) {
            setSeDeplace(true);
            seDeplace(1);
        } else {
            System.out.println("Collision détectée, déplacement annulé.");
        }

        // Vérifier si l'entité a atteint la prochaine positionq
        if (positionAtteinte(prochainePosition)) {
            aetoile.getChemin().remove(0); // Supprimer la position atteinte du chemin
            // Ajuster la position à des coordonnées arrondies au dixième
            setPosition(Math.round(getPosition().getX() * 10) / 10.0, Math.round(getPosition().getY() * 10) / 10.0);
        }

        System.out.println("Squelette se déplace vers : " + getPosition().getX() + ", " + getPosition().getY());
    }

    private boolean positionAtteinte(Position position) {
        return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    @Override
    public void attaque(Arme arme) {
        Position position1 = joueur.getPosition();
        if(Math.abs(getPosition().getX()+getDirection().getX()-position1.getX())<1) {
            if(Math.abs(getPosition().getY()+getDirection().getY()-position1.getY())<1) {
                this.joueur.subitDegat(new Epee());
            }
        }
    }

    @Override
    public void lanceUnSort(int numSort) {

    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public void unTour() {
        action();
    }

    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public String typeActeur() {
        return "Squelette";
    }
}
