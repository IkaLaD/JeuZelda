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

    // Construire le graphe en initialisant les sommets et leurs voisins
    private void construireGraphe() {
        int hauteur = Monde.getSizeMondeHauteur();
        int largeur = Monde.getSizeMondeLargeur();
        graphe = new Sommet[hauteur][largeur];

        // Initialiser les sommets
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                boolean traversable = monde.getNontraversable()[y][x] == -1;
                graphe[y][x] = new Sommet(new Position(x, y), traversable);
            }
        }

        // Ajouter les voisins pour chaque sommet traversable
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (graphe[y][x].isTraversable()) {
                    ajouterVoisins(graphe[y][x], x, y);
                }
            }
        }
    }

    // Ajouter les voisins pour un sommet donné
    private void ajouterVoisins(Sommet sommet, int x, int y) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < graphe[0].length && ny < graphe.length && graphe[ny][nx].isTraversable()) {
                sommet.addVoisin(graphe[ny][nx]);
            }
        }
    }

    // Mettre à jour le graphe pour refléter les changements dans le monde
    public void mettreAJourGraphe() {
        // Réinitialiser les sommets
        for (int y = 0; y < graphe.length; y++) {
            for (int x = 0; x < graphe[0].length; x++) {
                graphe[y][x].setTraversable(monde.getNontraversable()[y][x] == -1);
            }
        }

        // Marquer les positions des entités comme non traversables
        for (Entite entite : monde.getEntitesA()) {
            Position pos = entite.getPosition();
            int x = (int) pos.getX();
            int y = (int) pos.getY();
            if (x >= 0 && y >= 0 && x < graphe[0].length && y < graphe.length) {
                graphe[y][x].setTraversable(false);
            }
        }
    }

    // Trouver le chemin entre deux positions en utilisant l'algorithme A*
    public List<Position> trouverChemin(Position depart, Position arrivee) {
        mettreAJourGraphe();  // Mettre à jour le graphe avant de trouver le chemin

        Sommet sommetDepart = positionToSommet(depart);
        Sommet sommetArrivee = positionToSommet(arrivee);

        if (sommetDepart == null || sommetArrivee == null) {
            System.out.println("Positions invalides.");
            return Collections.emptyList();
        }

        System.out.println(STR."Départ : \{sommetDepart.getPosition().getX()}, \{sommetDepart.getPosition().getY()}");
        System.out.println(STR."Arrivée : \{sommetArrivee.getPosition().getX()}, \{sommetArrivee.getPosition().getY()}");

        PriorityQueue<Noeud> openList = new PriorityQueue<>(Comparator.comparingDouble(Noeud::getF));
        Map<Sommet, Noeud> allNodes = new HashMap<>();

        Noeud startNode = new Noeud(sommetDepart, null, 0, sommetDepart.distance(sommetArrivee));
        openList.add(startNode);
        allNodes.put(sommetDepart, startNode);

        while (!openList.isEmpty()) {
            Noeud currentNode = openList.poll();

            // Si le sommet actuel est le sommet de destination, reconstruire le chemin
            if (currentNode.getSommet().equals(sommetArrivee)) {
                return reconstruireChemin(currentNode);
            }

            // Explorer les voisins du sommet actuel
            for (Sommet voisin : currentNode.getSommet().getVoisins()) {
                double tentativeG = currentNode.getG() + currentNode.getSommet().distance(voisin);

                Noeud voisinNode = allNodes.getOrDefault(voisin, new Noeud(voisin));
                if (tentativeG < voisinNode.getG()) {
                    voisinNode.setParent(currentNode);
                    voisinNode.setG(tentativeG);
                    voisinNode.setH(voisin.distance(sommetArrivee));
                    allNodes.put(voisin, voisinNode);
                    if (!openList.contains(voisinNode)) {
                        openList.add(voisinNode);
                    }
                }
            }
        }
        System.out.println("Aucun chemin trouvé.");
        return Collections.emptyList();
    }

    // Reconstruire le chemin en partant du nœud de destination
    private ArrayList<Position> reconstruireChemin(Noeud noeud) {
        ArrayList<Position> chemin = new ArrayList<>();
        while (noeud != null) {
            chemin.add(getCentreSommet(noeud.getSommet()));
            noeud = noeud.getParent();
        }
        Collections.reverse(chemin);
        this.chemin = chemin; // Mettre à jour le chemin
        return chemin;
    }

    // Convertir une position en sommet
    public Sommet positionToSommet(Position position) {
        int x = (int) Math.floor(position.getX());
        int y = (int) Math.floor(position.getY());
        if (x >= 0 && y >= 0 && x < graphe[0].length && y < graphe.length) {
            return graphe[y][x];
        }
        return null;
    }

    // Obtenir le centre d'un sommet pour le chemin final
    public Position getCentreSommet(Sommet sommet) {
        int x = (int) sommet.getPosition().getX();
        int y = (int) sommet.getPosition().getY();
        return new Position(x + 0.5, y + 0.5);
    }

    // Obtenir le chemin trouvé
    public List<Position> getChemin() {
        return chemin;
    }

    // Classe interne représentant un nœud dans l'algorithme A*
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
            return g + h; // f = g + h, utilisé pour la priorité dans la file
        }
    }
}
