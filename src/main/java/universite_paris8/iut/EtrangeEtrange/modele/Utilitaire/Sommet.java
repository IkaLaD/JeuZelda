package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Position position;
    private boolean traversable;
    private List<Sommet> voisins;

    public Sommet(Position position, boolean traversable) {
        this.position = position;
        this.traversable = traversable;
        this.voisins = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void addVoisin(Sommet voisin) {
        this.voisins.add(voisin);
    }

    public List<Sommet> getVoisins() {
        return voisins;
    }

    public double distance(Sommet autre) {
        return this.position.distance(autre.getPosition());
    }
}
