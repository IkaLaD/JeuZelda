package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;

import java.util.*;

public class Aetoile {
    private Monde monde;
    private Sommet[][] graphe;
    private ArrayList<Position> chemin;

    public Aetoile(Monde monde) {
        this.monde = monde;
        this.chemin = new ArrayList<>();
        construireGraphe(); // Construire le graphe lors de l'initialisation
    }

    private void construireGraphe() {
        int hauteur = Monde.getSizeMondeHauteur();
        int largeur = Monde.getSizeMondeLargeur();
        graphe = new Sommet[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                boolean traversable = monde.getNontraversable()[y][x] == -1;
                graphe[y][x] = new Sommet(new Position(x, y), traversable);
            }
        }

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (graphe[y][x].isTraversable()) {
                    ajouterVoisins(graphe[y][x], x, y);
                }
            }
        }
    }

    private void ajouterVoisins(Sommet sommet, int x, int y) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (nx >= 0 && ny >= 0 && nx < graphe[0].length && ny < graphe.length && graphe[ny][nx].isTraversable()) {
                sommet.addVoisin(graphe[ny][nx]);
            }
        }
    }

    public void mettreAJourGraphe() {
        for (int y = 0; y < graphe.length; y++) {
            for (int x = 0; x < graphe[0].length; x++) {
                graphe[y][x].setTraversable(monde.getNontraversable()[y][x] == -1);
            }
        }

        for (Entite entite : monde.getEntitesA()) {
            Position position = entite.getPosition();
            int x = (int) position.getX();
            int y = (int) position.getY();
            if (x >= 0 && y >= 0 && x < graphe[0].length && y < graphe.length) {
                graphe[y][x].setTraversable(false);
            }
        }
    }

    public List<Position> trouverChemin(Position depart, Position arrivee) {
        mettreAJourGraphe();

        Sommet sommetDepart = positionVersSommet(depart);
        Sommet sommetArrivee = positionVersSommet(arrivee);

        if (sommetDepart == null || sommetArrivee == null) {
            System.out.println("Positions invalides.");
            return Collections.emptyList();
        }

        PriorityQueue<Noeud> listeOuverte = new PriorityQueue<>(Comparator.comparingDouble(Noeud::getF));
        Map<Sommet, Noeud> tousLesNoeuds = new HashMap<>();

        Noeud noeudDepart = new Noeud(sommetDepart, null, 0, sommetDepart.distance(sommetArrivee));
        listeOuverte.add(noeudDepart);
        tousLesNoeuds.put(sommetDepart, noeudDepart);

        while (!listeOuverte.isEmpty()) {
            Noeud noeudActuel = listeOuverte.poll();

            if (noeudActuel.getSommet().equals(sommetArrivee)) {
                return reconstruireChemin(noeudActuel);
            }

            for (Sommet voisin : noeudActuel.getSommet().getVoisins()) {
                double gTentative = noeudActuel.getG() + noeudActuel.getSommet().distance(voisin);

                Noeud noeudVoisin = tousLesNoeuds.getOrDefault(voisin, new Noeud(voisin));
                if (gTentative < noeudVoisin.getG()) {
                    noeudVoisin.setParent(noeudActuel);
                    noeudVoisin.setG(gTentative);
                    noeudVoisin.setH(voisin.distance(sommetArrivee));
                    tousLesNoeuds.put(voisin, noeudVoisin);
                    if (!listeOuverte.contains(noeudVoisin)) {
                        listeOuverte.add(noeudVoisin);
                    }
                }
            }
        }

        System.out.println("Aucun chemin trouvé.");
        return Collections.emptyList();
    }

    private ArrayList<Position> reconstruireChemin(Noeud noeud) {
        ArrayList<Position> chemin = new ArrayList<>();
        while (noeud != null) {
            chemin.add(obtenirCentreSommet(noeud.getSommet()));
            noeud = noeud.getParent();
        }
        Collections.reverse(chemin);
        this.chemin = chemin;
        return chemin;
    }

    public Sommet positionVersSommet(Position position) {
        int x = (int) Math.floor(position.getX());
        int y = (int) Math.floor(position.getY());
        if (x >= 0 && y >= 0 && x < graphe[0].length && y < graphe.length) {
            return graphe[y][x];
        }
        return null;
    }

    public Position obtenirCentreSommet(Sommet sommet) {
        int x = (int) sommet.getPosition().getX();
        int y = (int) sommet.getPosition().getY();
        return new Position(x + 0.5, y + 0.5);
    }

    public List<Position> getChemin() {
        return chemin;
    }

    private static class Noeud {
        private Sommet sommet;
        private Noeud parent;
        private double g; // Coût du chemin depuis le début
        private double h; // Heuristique (estimation du coût restant)

        public Noeud(Sommet sommet) {
            this.sommet = sommet;
            this.g = Double.MAX_VALUE;
        }

        public Noeud(Sommet sommet, Noeud parent, double g, double h) {
            this.sommet = sommet;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        public Sommet getSommet() {
            return sommet;
        }

        public Noeud getParent() {
            return parent;
        }

        public void setParent(Noeud parent) {
            this.parent = parent;
        }

        public double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }

        public double getH() {
            return h;
        }

        public void setH(double h) {
            this.h = h;
        }

        public double getF() {
            return g + h;
        }
    }
}
