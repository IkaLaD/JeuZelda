package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.SeDeplacerVers;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class Squelette extends EntiteOffensif implements PNJ, SeDeplacerVers {

    private Joueur joueur;
    private Aetoile aetoile;
    private long lastPathCalculationTime;

    public Squelette( Monde monde, double x, double y, Direction direction, Hitbox hitbox, Joueur joueur, Aetoile aetoile) {
        super( monde,  x,  y,  direction,  100,  10,  10,1 ,  10,  0.5, hitbox);

        this.joueur = joueur;
        this.aetoile = aetoile;
        this.lastPathCalculationTime = System.currentTimeMillis();
    }

    @Override
    public void action() {
        seDeplacerVers(joueur.getPosition());
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
    public void seDeplacerVers(Position joueurPosition) {
        if (aetoile == null) {

            return;
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPathCalculationTime >= 3000 || aetoile.getChemin().isEmpty()) {
            aetoile.trouverChemin(getPosition(), joueurPosition);
            lastPathCalculationTime = currentTime;
            if (aetoile.getChemin().isEmpty()) {

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

        }

        // Vérifier si l'entité a atteint la prochaine positionq
        if (positionAtteinte(prochainePosition)) {
            aetoile.getChemin().remove(0); // Supprimer la position atteinte du chemin
            // Ajuster la position à des coordonnées arrondies au dixième
            setPosition(Math.round(getPosition().getX() * 10) / 10.0, Math.round(getPosition().getY() * 10) / 10.0);
        }


    }

    private boolean positionAtteinte(Position position) {
        return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    @Override
    public void attaque(Arme arme) {

    }

    @Override
    public void lanceUnSort(int numSort) {

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
