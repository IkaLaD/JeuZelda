package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Families;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Orbe;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public abstract class Familie extends EntiteOffensif {

    protected Joueur joueur;
    protected boolean estFamilier;
    protected double rayonDetection;
    private Aetoile aetoile;
    private long lastPathCalculationTime;

    public Familie(Joueur joueur, double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox, double rayonDetection, Aetoile aetoile) {
        super( monde,  x,  y,  direction,  pv,  attaque,  defense,  attaqueSpecial ,  defenseSpecial,  vitesse, hitbox);
        this.joueur = joueur;
        this.rayonDetection = rayonDetection;
        this.estFamilier = false;
        this.aetoile = aetoile;
        this.lastPathCalculationTime = System.currentTimeMillis();
    }


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
            seDeplace(1);
        }

        // Vérifier si l'entité a atteint la prochaine position
        if (positionAtteinte(prochainePosition)) {
            aetoile.getChemin().remove(0); // Supprimer la position atteinte du chemin
            // Ajuster la position à des coordonnées arrondies au dixième
            setPosition(roundToTenth(getPosition().getX()), roundToTenth(getPosition().getY()));
        }

    }

    private double roundToTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private boolean positionAtteinte(Position position) {
        return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    private boolean detecteJoueur(Joueur joueur) {
        Position positionJoueur = joueur.getPosition();
        double distance = Math.sqrt(Math.pow(getPosition().getX() - positionJoueur.getX(), 2) +
                Math.pow(getPosition().getY() - positionJoueur.getY(), 2));
        return distance <= rayonDetection;
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
            seDeplace(1);
        }
    }

    public Position positionDerriereJoueur() {
        Position positionJoueur = joueur.getPosition();
        Direction directionJoueur = joueur.getDirection();

        // Calcul des coordonnées de la position derrière le joueur en fonction de sa direction
        double newX = positionJoueur.getX() - directionJoueur.getX();
        double newY = positionJoueur.getY() - directionJoueur.getY();

        // Retourner une nouvelle instance de Position avec les coordonnées calculées
        return new Position(newX, newY);
    }


    @Override
    public void unTour() {
        if (!estFamilier) {
            if (detecteJoueur(joueur)) {
                estFamilier = true;
            }
            seDeplaceAleatoire();
        }
        else {
            setSeDeplace(true);
            seDeplacerVers(positionDerriereJoueur());
            if (detecteEntiteDansRayon(5)){
                new Orbe().utilise(this);
            }
        }
    }

    public boolean detecteEntiteDansRayon(double rayon) {
        ArrayList<Entite> entites = monde.getEntites(); // Suppose une méthode qui retourne toutes les entités du monde
        Position maPosition = getPosition();

        for (Entite entite : entites) {
            if (entite != this) { // Pour ne pas se détecter soi-même
                double distance = Math.sqrt(Math.pow(maPosition.getX() - entite.getPosition().getX(), 2) +
                        Math.pow(maPosition.getY() - entite.getPosition().getY(), 2));
                if (distance <= rayon) {
                    return true; // Retourne vrai dès qu'une entité est trouvée dans le rayon
                }
            }
        }
        return false; // Aucune entité trouvée dans le rayon
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
    public String typeActeur() {
        return null;
    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public void attaque(Arme arme) {

    }

    @Override
    public void lanceUnSort(int numSort) {

    }
}