package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.List;
import java.util.Random;

public class Loup extends Familie {
    private boolean estFamilier;
    private Random random;
    private double rayonDetection;

    public Loup(Joueur joueur, Monde monde, double x, double y, Direction direction, Hitbox hitbox, Aetoile aetoile, double rayonDetection) {
        super(joueur, 100, 15, 10, 10, 10, 1.5, monde, x, y, direction, hitbox, aetoile);
        this.estFamilier = false;
        this.random = new Random();
        this.rayonDetection = rayonDetection;
    }

    @Override
    public void attaque() {
        // Implémenter l'attaque spécifique du loup
    }

    @Override
    public void seDeplacerVersJoueur(Joueur joueur, Aetoile aetoile, int[][] grille) {
        if (!estFamilier) {
            seDeplace();
            if (detecteJoueur(joueur)) {
                devientFamilier();
            }
        } else {
            mettreAJourPosition();
        }
    }

    private boolean detecteJoueur(Joueur joueur) {
        Position positionJoueur = joueur.getPosition();
        double distance = Math.sqrt(Math.pow(getPosition().getX() - positionJoueur.getX(), 2) +
                Math.pow(getPosition().getY() - positionJoueur.getY(), 2));
        boolean detected = distance <= rayonDetection;
        if (detected) {
            System.out.println("Loup a détecté le joueur à distance : " + distance);
        }
        return detected;
    }

    private void devientFamilier() {
        this.estFamilier = true;
        System.out.println("Loup est devenu un familier");
    }

    public void mettreAJourPosition() {
        Position positionJoueur = joueur.getPosition();
        Direction directionJoueur = joueur.getDirection();
        Position positionCible = calculerPositionDerriere(positionJoueur, directionJoueur);

        List<int[]> chemin = getAetoile().trouverChemin((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) positionCible.getX(), (int) positionCible.getY());
        if (!chemin.isEmpty()) {
            int[] prochainePosition = chemin.get(0);
            this.setPosition(prochainePosition[0], prochainePosition[1]);
            System.out.println("Loup se déplace vers : " + prochainePosition[0] + ", " + prochainePosition[1]);
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
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return 0;
    }

    @Override
    public void consommer() {

    }
}
