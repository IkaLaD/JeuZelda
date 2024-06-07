package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Boss;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain.Squelette;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class RoiSquelette extends EntiteOffensif implements PNJ {

    private long dernierTempsAttaque;
    private long delaiAttaque = 200;
    private Position positionInitiale;
    private int etapeAttaque;
    private Position positionMilieu;
    private Position position5_2;
    private boolean joueurDetecte = false;
    private double distanceDetection = 5.0;

    public RoiSquelette(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.dernierTempsAttaque = System.currentTimeMillis();
        this.positionInitiale = new Position(x, y);
        this.etapeAttaque = 0;
        this.positionMilieu = new Position(5.5, 27.5);
        this.position5_2 = new Position(2, 27.5);
        setPosition(x, y); // Positionnement initial du Roi Squelette
    }

    @Override
    public void attaque(Arme arme) {

    }

    @Override
    public void lanceUnSort(int numSort) {

    }

    @Override
    public void action() {
        // Vérifie si le joueur a été détecté
        if (!joueurDetecte) {
            if (detecteJoueurDansRayon(distanceDetection)) {
                System.out.println("Le joueur a été détecté à proximité du boss.");
                joueurDetecte = true;
            } else {
                return; // Ne rien faire si le joueur n'est pas détecté
            }
        }

        long tempsActuel = System.currentTimeMillis();
        // Vérifie si le délai d'attaque est écoulé
        if (tempsActuel - dernierTempsAttaque >= delaiAttaque) {
            switch (etapeAttaque) {
                case 0:
                    System.out.println("Déplacement vers le milieu pour une attaque circulaire.");
                    seDeplacerVers(positionMilieu);
                    if (positionAtteinte(positionMilieu)) {
                        grandeAttaqueCirculaire();
                        etapeAttaque++;
                    }
                    break;
                case 1:
                    System.out.println("Déplacement vers la position (5,2) pour la deuxième attaque.");
                    seDeplacerVers(position5_2);
                    if (positionAtteinte(position5_2)) {
                        // Implémenter ici la deuxième attaque
                        etapeAttaque++;
                    }
                    break;
                case 2:
                    System.out.println("Retour au milieu pour invoquer des squelettes.");
                    seDeplacerVers(positionMilieu);
                    if (positionAtteinte(positionMilieu)) {
                        invoquerSquelettes();
                        etapeAttaque++;
                    }
                    break;
                case 3:
                    System.out.println("Retour au milieu pour une attaque circulaire.");
                    seDeplacerVers(positionInitiale);
                    if (positionAtteinte(positionInitiale)) {
                        grandeAttaqueCirculaire();
                        etapeAttaque = 0; // Recommencer le cycle
                    }
                    break;
            }
            dernierTempsAttaque = tempsActuel;
        }
    }

    // Détecte si le joueur est dans un certain rayon autour du Roi Squelette
    private boolean detecteJoueurDansRayon(double rayon) {
        Position positionJoueur = getMonde().getJoueur().getPosition();
        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - getPosition().getX(), 2) +
                Math.pow(positionJoueur.getY() - getPosition().getY(), 2));
        return distance <= rayon;
    }

    // Effectue une grande attaque circulaire
    private void grandeAttaqueCirculaire() {
        System.out.println("Roi Squelette effectue une grande attaque circulaire.");
        Position centre = getPosition();
        double rayon = 1.5; // Rayon d'exemple pour l'attaque circulaire
        // Vérifie si la hitbox du joueur est dans le rayon de l'attaque
        if (getMonde().getJoueur().getHitbox().estDansCercle(centre, rayon)) {
            System.out.println("Dégâts infligés au joueur.");
        }
    }

    // Invoque des squelettes pour aider le Roi Squelette
    private void invoquerSquelettes() {
        System.out.println("Roi Squelette invoque des squelettes.");
        Position positionGauche = new Position(getPosition().getX() - 2, getPosition().getY());
        Position positionDroite = new Position(getPosition().getX() + 2, getPosition().getY());
        Squelette squeletteGauche = new Squelette(30, 10, 5, 10, 10, 0.1, getMonde(), positionGauche.getX(), positionGauche.getY(), Direction.DROITE, new Hitbox(0.5, 0.5), getMonde().getJoueur(), new Aetoile(getMonde()));
        Squelette squeletteDroite = new Squelette(30, 10, 5, 10, 10, 0.1, getMonde(), positionDroite.getX(), positionDroite.getY(), Direction.GAUCHE, new Hitbox(0.5, 0.5), getMonde().getJoueur(), new Aetoile(getMonde()));
        getMonde().ajoutEntite(squeletteGauche);
        getMonde().ajoutEntite(squeletteDroite);
    }

    // Déplace le Roi Squelette vers une destination donnée
    private void seDeplacerVers(Position destination) {
        double deltaX = destination.getX() - getPosition().getX();
        double deltaY = destination.getY() - getPosition().getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
        } else {
            setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);
        }
            seDeplace();
    }

    // Vérifie si le Roi Squelette a atteint une certaine position
    private boolean positionAtteinte(Position position) {
        return Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1;
    }

    @Override
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return (degat * forceEntite) / (getDefense() - (degat/6));
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return (attaqueSpecial * forceEntite) / (getDefense() - (attaqueSpecial/6));
    }

}
