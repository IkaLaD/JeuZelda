package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Families;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Controlable;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.List;

public class Familie extends EntiteOffensif implements Controlable {

    protected Joueur joueur;
    protected boolean estFamilier;
    protected double rayonDetection;
    protected Aetoile aetoile;
    private List<Position> chemin;
    private int currentStep;
    private double vitesse;

    public Familie(Joueur joueur, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox, double rayonDetection, Aetoile aetoile) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.joueur = joueur;
        this.rayonDetection = rayonDetection;
        this.estFamilier = false;
        this.aetoile = aetoile;
        this.chemin = null;
        this.currentStep = 0;
        this.vitesse = vitesse;
    }

    @Override
    public void action() {
        if (!estFamilier) {
            if (detecteJoueur(joueur)) {
                estFamilier = true;
                System.out.println("Familier a détecté le joueur et devient familier");
                chemin = aetoile.trouverChemin(getPosition(), joueur.getPosition());
                currentStep = 0;
            } else {
                seDeplaceAleatoire();
            }
        } else {
            seDeplacerVersJoueur();
        }
    }

    private boolean detecteJoueur(Joueur joueur) {
        Position positionJoueur = joueur.getPosition();
        double distance = Math.sqrt(Math.pow(getPosition().getX() - positionJoueur.getX(), 2) +
                Math.pow(getPosition().getY() - positionJoueur.getY(), 2));
        return distance <= rayonDetection;
    }

    public void seDeplacerVersJoueur() {
        System.out.println("Tentative de déplacement vers le joueur");

        if (chemin == null || chemin.isEmpty()) {
            chemin = aetoile.trouverChemin(getPosition(), joueur.getPosition());
            currentStep = 0;
            if (chemin.isEmpty()) {
                System.out.println("Aucun chemin trouvé pour atteindre le joueur.");
                return;
            }
        }

        if (currentStep < chemin.size()) {
            Position prochainePosition = chemin.get(currentStep);
            double deltaX = prochainePosition.getX() - getPosition().getX();
            double deltaY = prochainePosition.getY() - getPosition().getY();
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance < vitesse) {
                // Si le familier est très proche de la prochaine position, le téléporter directement
                setPosition(prochainePosition.getX(), prochainePosition.getY());
                currentStep++;
            } else {
                // Sinon, déplacer le familier progressivement vers la prochaine position
                double ratio = vitesse / distance;
                double newX = getPosition().getX() + deltaX * ratio;
                double newY = getPosition().getY() + deltaY * ratio;
                setPosition(newX, newY);
            }

            System.out.println("Familier se déplace vers : " + getPosition().getX() + ", " + getPosition().getY());
        } else {
            chemin = aetoile.trouverChemin(getPosition(), joueur.getPosition());
            currentStep = 0;
        }
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
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return 0;
    }

    @Override
    public void consommer() {
        // Implémenter la logique de consommation si nécessaire
    }
}
