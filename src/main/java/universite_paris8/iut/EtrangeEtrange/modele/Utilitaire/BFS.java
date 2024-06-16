package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesAffichage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private Monde monde;
    private ArrayList<Sommet> chemins;
    private Sommet[][] graphe;
    private int xArrive, yArrive;

    public BFS() {
        monde = null;
        chemins = new ArrayList<>();
    }

    public void chercherChemin(Monde monde, Position positionDepart, Position positionArrive) {
        this.monde = monde;
        this.xArrive = (int) positionArrive.getX();
        this.yArrive = (int) positionArrive.getY();
        construireGraphe();
        trouverChemin((int) positionDepart.getX(), (int) positionDepart.getY());
    }

    private void construireGraphe() {
        int hauteur = Monde.getSizeMondeHauteur();
        int largeur = Monde.getSizeMondeLargeur();
        this.graphe = new Sommet[hauteur][largeur];

        for (int y = 0; y < hauteur; y++)
            for (int x = 0; x < largeur; x++)
                this.graphe[y][x] = new Sommet(new Position(x, y), monde.getNontraversable()[y][x] == -1);


        for (int y = 0; y < hauteur; y++)
            for (int x = 0; x < largeur; x++)
                if (graphe[y][x].isTraversable())
                    ajouterVoisins(graphe[y][x], x, y);
    }

    private void ajouterVoisins(Sommet sommet, int x, int y)
    {
        for (Direction dir : Direction.values())
        {
            int nx = x + dir.getX();
            int ny = y + dir.getY();

            if (nx >= 0 && ny >= 0 && nx < graphe[0].length && ny < graphe.length && graphe[ny][nx].isTraversable())
                sommet.addVoisin(graphe[ny][nx]);
        }
    }

    private void trouverChemin(int x, int y) {
        Queue<Sommet> sommetsAvisite = new LinkedList<>();
        HashMap<Sommet, Sommet> predecesseurs = new HashMap<>();
        boolean cheminTrouver = false;

        sommetsAvisite.add(graphe[y][x]);
        predecesseurs.put(graphe[y][x], null);

        while (!sommetsAvisite.isEmpty() && !cheminTrouver)
        {
            Sommet actuelle = sommetsAvisite.poll();

            for (int i = 0;i<actuelle.getVoisins().size() && !cheminTrouver;i++)
            {
                Sommet voisin = actuelle.getVoisins().get(i);

                if (!predecesseurs.containsKey(voisin))
                {
                    predecesseurs.put(voisin, actuelle);
                    sommetsAvisite.add(voisin);

                    if (cheminTrouver(voisin.getPosition().getX(), voisin.getPosition().getY()))
                    {
                        cheminTrouver = true;
                        construireChemin(voisin, predecesseurs);
                    }
                }
            }
        }
    }

    private void construireChemin(Sommet sommet, HashMap<Sommet, Sommet> predecesseurs)
    {
        this.chemins.clear();
        Sommet actuel = sommet;

        while (actuel != null)
        {
            this.chemins.add(0, actuel);
            actuel = predecesseurs.get(actuel);
        }
    }

    private boolean cheminTrouver(double x, double y) {return x == xArrive && y == yArrive;}

    public Position prochainePosition()
    {
        Position position = null;

        if (chemins != null && !chemins.isEmpty())
            position =  chemins.remove(0).getPosition();


        return position;
    }
}
