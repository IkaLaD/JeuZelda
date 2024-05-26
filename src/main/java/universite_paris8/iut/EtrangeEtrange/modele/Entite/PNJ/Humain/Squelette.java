package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.SeDeplacerVersJoueur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class Squelette extends EntiteOffensif implements Controlable, SeDeplacerVersJoueur {

    private Joueur joueur;
    private Aetoile aetoile;
    private long lastPathCalculationTime;

    public Squelette(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox, Joueur joueur, Aetoile aetoile) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.joueur = joueur;
        this.aetoile = aetoile;
        this.lastPathCalculationTime = System.currentTimeMillis();
    }

    @Override
    public void action() {
        seDeplacerVersJoueur(joueur.getPosition());
    }

    @Override
    protected double subitDegatPhysique(double degat, double forceEntite) {
        return degat - getDefense().getDefenseActuelle();
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial, double forceEntite) {
        return attaqueSpecial - getDefenseSpecial().getDefenseSpecialActuelle();
    }

    @Override
    public void consommer() {
        // Not applicable for this entity
    }

    @Override
    public void attaque() {
        // Implement attack logic for skeleton
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
            seDeplace();
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
}
