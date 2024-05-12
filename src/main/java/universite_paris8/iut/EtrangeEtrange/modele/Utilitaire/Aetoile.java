package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

import javafx.geometry.Pos;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Aetoile {
    private Monde monde;
    /**
     * l'entité choisi sera le point d'arriver de toutes les recherche de chemin
     */
    private Entite arrivee;

    public Aetoile(Monde monde, Entite arrivee){
        this.monde= monde;
        this.arrivee = arrivee;
    }

    public ArrayList<Position> cheminAetoile(Entite depart)
    {
        return new ArrayList<>();
    }

    /**
     * Retourne une liste de tous les sommets adjacents au sommet mis en paramètres
     * @param sommet
     * @return
     */
    public ArrayList<Position> getAdjacents(Sommet sommet){
        ArrayList<Position> adjacents = new ArrayList<>();
        for(int i = -1 ; i <= 1 ; i+=2){
            if(sommet.getX()+i>= 0 && sommet.getX()+i<=Monde.getSizeMondeLargeur())
                adjacents.add(new Position(sommet.getX()+i, sommet.getY()));
            if(sommet.getY()+i>= 0 && sommet.getY()+i<=Monde.getSizeMondeLargeur())
                adjacents.add(new Position(sommet.getX(), sommet.getY()+i));
        }
        return adjacents;
    }

    /**
     * Retourne le poids d'un sommet en fonction des 3 couches du monde
     * @param sommet
     * @return
     */
    public double getPoidsSommet(Sommet sommet){
        if(monde.getNontraversable()[sommet.getY()][sommet.getX()] != -1)
            return -1;
        return poidsTuiles(sommet);
    }

    /**
     * Retourne le poids en fonction de la couche du sol et de la couche des éléments traversable
     * (on ne considère pas pour le moment que les éléments non traversables peuvent peut-être être détruit et donc donner
     * une nouvelle possibilité de chemin)
     * @param sommet
     * @return
     */
    public double poidsTuiles(Sommet sommet){
        double poids = 0;
        int idTuiles = monde.getTraversable()[sommet.getY()][sommet.getX()];
        switch (idTuiles){
            default -> poids+=0;
        }
        idTuiles = monde.getSol()[sommet.getY()][sommet.getX()];
        switch (idTuiles){
            default -> poids+=0;
        }
        return poids;
    }

    /**
     * Retourne la distance de manhattan entre le sommet mis en paramètres et l'entité arrivée.
     * @param sommet
     * @return
     */
    public double getHeuristique(Sommet sommet){
        return Math.abs(sommet.getX()-arrivee.getPosition().getX())+
                Math.abs(sommet.getY()-arrivee.getPosition().getY());
    }



}
