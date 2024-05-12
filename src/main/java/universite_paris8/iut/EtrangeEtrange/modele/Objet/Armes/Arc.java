package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

public class Arc extends Arme {
    private double degats;
    private double portee;

    public Arc(double degats, double portee) {
        this.degats = degats;
        this.portee = portee;
    }

    @Override
    public double degatPhysique() {
        return degats;
    }

    @Override
    public double degatSpecial() {
        return 0; // Pas de dégât spécial pour un arc
    }

    @Override
    public double portee() {
        return portee;
    }

    @Override
    public double angle() {
        return 0; // Pour un arc, l'angle n'est pas pertinent
    }

    @Override
    public double delaieEntreCoup() {
        return 0; // Pour un arc, le délai entre les coups n'est pas pertinent
    }

    @Override
    public String getNom() {
        return "Arc"; // Nom générique pour un arc
    }

    @Override
    public int stackMax() {
        return 1; // On suppose qu'on ne peut porter qu'un arc à la fois
    }

    public void infligerDegatsEntiteDevant(Position positionJoueur, Direction directionJoueur, ArrayList<Entite> entites) {
        double distanceMin = Double.MAX_VALUE;
        Entite entiteProche = null;

        for (Entite entite : entites) {
            // Calcul de la distance entre le joueur et l'entité
            double distance = calculerDistance(positionJoueur, entite.getPosition());

            // Vérification si l'entité est dans la direction du joueur et plus proche que la distance minimale actuelle
            if (estDansDirection(directionJoueur, positionJoueur, entite.getPosition()) && distance < distanceMin) {
                distanceMin = distance;
                entiteProche = entite;
            }
        }

        // Si une entité a été trouvée dans la portée de l'arc
        if (entiteProche != null) {
            // Infliger des dégâts à l'entité trouvée
            entiteProche.subitDegat(degats, 0); // On suppose que l'attaque spéciale est de 0
        }
    }

    private double calculerDistance(Position pos1, Position pos2) {
        double dx = pos2.getX() - pos1.getX();
        double dy = pos2.getY() - pos1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private boolean estDansDirection(Direction direction, Position positionJoueur, Position positionEntite) {
        switch (direction) {
            case HAUT:
                return positionEntite.getY() < positionJoueur.getY() && positionEntite.getX() == positionJoueur.getX();
            case BAS:
                return positionEntite.getY() > positionJoueur.getY() && positionEntite.getX() == positionJoueur.getX();
            case GAUCHE:
                return positionEntite.getX() < positionJoueur.getX() && positionEntite.getY() == positionJoueur.getY();
            case DROITE:
                return positionEntite.getX() > positionJoueur.getX() && positionEntite.getY() == positionJoueur.getY();
            default:
                return false;
        }
    }
}
