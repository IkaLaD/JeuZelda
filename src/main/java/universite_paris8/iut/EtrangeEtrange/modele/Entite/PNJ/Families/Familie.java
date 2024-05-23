package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.List;

public abstract class Familie extends EntiteOffensif {
    protected Joueur joueur;
    private Aetoile aetoile;

    public Familie(Joueur joueur, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox, Aetoile aetoile) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.joueur = joueur;
        this.aetoile = aetoile;
    }

    public Aetoile getAetoile() {
        return aetoile;
    }

    public void mettreAJourPosition() {
        Position positionJoueur = joueur.getPosition();
        Direction directionJoueur = joueur.getDirection();
        Position positionCible = calculerPositionDerriere(positionJoueur, directionJoueur);

        List<int[]> chemin = aetoile.trouverChemin((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) positionCible.getX(), (int) positionCible.getY());
        if (!chemin.isEmpty()) {
            int[] prochainePosition = chemin.get(0);
            this.setPosition(prochainePosition[0], prochainePosition[1]);
        }
    }

    private Position calculerPositionDerriere(Position positionJoueur, Direction directionJoueur) {
        double nouvelleX = positionJoueur.getX();
        double nouvelleY = positionJoueur.getY();
        switch (directionJoueur) {
            case HAUT:
                nouvelleY += 1;
                break;
            case BAS:
                nouvelleY -= 1;
                break;
            case DROITE:
                nouvelleX += 1;
                break;
            case GAUCHE:
                nouvelleX -= 1;
                break;
        }
        return new Position(nouvelleX, nouvelleY);
    }

    @Override
    public void attaque() {
        // Implémenter la logique d'attaque
    }

    @Override
    public void seDeplacerVersJoueur(Joueur joueur, Aetoile aetoile, int[][] grille) {
        mettreAJourPosition();
    }
}
