package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.util.*;

public class Aetoile {
    private Monde monde;
    private Entite arrivee;

    public Aetoile(Monde monde, Entite arrivee){
        this.monde = monde;
        this.arrivee = arrivee;
    }

    public ArrayList<Position> cheminAetoile(Entite depart) {
        return new ArrayList<>();
    }

    public ArrayList<Position> getAdjacents(Sommet sommet) {
        ArrayList<Position> adjacents = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            if (sommet.getX() + i >= 0 && sommet.getX() + i < Monde.getSizeMondeLargeur()) {
                adjacents.add(new Position(sommet.getX() + i, sommet.getY()));
            }
            if (sommet.getY() + i >= 0 && sommet.getY() < Monde.getSizeMondeHauteur()) {
                adjacents.add(new Position(sommet.getX(), sommet.getY() + i));
            }
        }
        return adjacents;
    }

    public double getPoidsSommet(Sommet sommet) {
        if (monde.getNontraversable()[sommet.getY()][sommet.getX()] != -1) {
            return -1;
        }
        return poidsTuiles(sommet);
    }

    public double poidsTuiles(Sommet sommet) {
        double poids = 1; // Poids par défaut pour une tuile traversable
        int idTuiles = monde.getTraversable()[sommet.getY()][sommet.getX()];
        switch (idTuiles) {
            default -> poids += 0;
        }
        idTuiles = monde.getSol()[sommet.getY()][sommet.getX()];
        switch (idTuiles) {
            default -> poids += 0;
        }
        return poids;
    }

    public double getHeuristique(Sommet sommet) {
        return Math.abs(sommet.getX() - arrivee.getPosition().getX()) +
                Math.abs(sommet.getY() - arrivee.getPosition().getY());
    }

    public List<int[]> trouverChemin(int x, int y, int x1, int y1) {
        PriorityQueue<Sommet> openList = new PriorityQueue<>(Comparator.comparingDouble(Sommet::getF));
        Set<Sommet> closedList = new HashSet<>();
        Map<Sommet, Sommet> cameFrom = new HashMap<>();
        Map<Sommet, Double> gScore = new HashMap<>();
        Map<Sommet, Double> fScore = new HashMap<>();

        Sommet start = new Sommet(x, y);
        Sommet goal = new Sommet(x1, y1);
        openList.add(start);
        gScore.put(start, 0.0);
        fScore.put(start, getHeuristique(start));

        while (!openList.isEmpty()) {
            Sommet current = openList.poll();
            if (current.equals(goal)) {
                return reconstruireChemin(cameFrom, current);
            }

            closedList.add(current);

            for (Position adj : getAdjacents(current)) {
                Sommet neighbor = new Sommet((int) adj.getX(), (int) adj.getY());
                if (closedList.contains(neighbor)) {
                    continue;
                }

                double tentativeGScore = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + getPoidsSommet(neighbor);

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + getHeuristique(neighbor));
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private List<int[]> reconstruireChemin(Map<Sommet, Sommet> cameFrom, Sommet current) {
        List<int[]> totalPath = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            totalPath.add(new int[]{current.getX(), current.getY()});
            current = cameFrom.get(current);
        }
        Collections.reverse(totalPath);
        return totalPath;
    }
}
