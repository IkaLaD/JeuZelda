package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
    private Position position;
    private boolean traversable;
    private ArrayList<Sommet> voisins;

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

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public ArrayList<Sommet> getVoisins() {
        return voisins;
    }

    public void addVoisin(Sommet voisin) {
        this.voisins.add(voisin);
    }

    public double distance(Sommet autre) {
        return Math.sqrt(Math.pow(position.getX() - autre.getPosition().getX(), 2) +
                Math.pow(position.getY() - autre.getPosition().getY(), 2));
    }

}
