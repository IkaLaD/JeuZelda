package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Controlable;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Familie extends EntiteOffensif implements Controlable {

    private Joueur joueur;
    private double rayonDetection;

    public Familie(Joueur joueur, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox, double rayonDetection) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.joueur = joueur;
        this.rayonDetection = rayonDetection;
    }

    @Override
    public void action() {
        if (detecteJoueur(joueur)) {
            disparaitre();
        } else {
            seDeplaceAleatoire();
        }
    }

    private boolean detecteJoueur(Joueur joueur) {
        Position positionJoueur = joueur.getPosition();
        double distance = Math.sqrt(Math.pow(getPosition().getX() - positionJoueur.getX(), 2) +
                Math.pow(getPosition().getY() - positionJoueur.getY(), 2));
        System.out.println("Position du joueur: " + positionJoueur.getX() + ", " + positionJoueur.getY());
        System.out.println("Position de la famille: " + getPosition().getX() + ", " + getPosition().getY());
        System.out.println("Distance calculée: " + distance);

        boolean detected = distance <= rayonDetection;
        if (detected) {
            System.out.println("Familier a détecté le joueur à distance : " + distance);
        }
        return detected;
    }

    private void disparaitre() {
        System.out.println("Familier disparaît");
        getMonde().enleveEntite(this);
    }

    private void seDeplaceAleatoire() {
        if (Math.random() > 0.95) {
            setSeDeplace(!isSeDeplace());
        }

        double probaChangement = Math.random();
        if (probaChangement > 0.80) {
            int d = (int) (Math.random() * 4);
            switch (d) {
                case 0:
                    setDirection(Direction.DROITE);
                    break;
                case 1:
                    setDirection(Direction.GAUCHE);
                    break;
                case 2:
                    setDirection(Direction.HAUT);
                    break;
                case 3:
                    setDirection(Direction.BAS);
                    break;
            }
        }

        if (isSeDeplace()) {
            seDeplace();
        }
    }

    @Override
    public void attaque() {
        // Implémenter la logique d'attaque si nécessaire
    }

    @Override
    public void seDeplacerVersJoueur(Joueur joueur, Aetoile aetoile, int[][] grille) {

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
