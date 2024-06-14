package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceActif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import java.util.HashSet;
import java.util.Set;


public class Deplacement
{
    private Joueur joueur;
    private Set<Direction> directions;

    private BooleanProperty estEntrainDeCourir;
    private int coefficientCourse;
    private double delai;



    public Deplacement(Joueur joueur)
    {
        this.joueur = joueur;
        this.estEntrainDeCourir = new SimpleBooleanProperty();
        this.coefficientCourse = 1;
        this.estEntrainDeCourir.bind(this.joueur.estEntrainDeCourirProperty());
        this.directions = new HashSet<>();
        this.delai = 0.020;


    }

    public void seDeplace()
    {
        coefficientCourse = 1;

        if(directions.isEmpty())
            joueur.setSeDeplace(false);
        else
            joueur.setSeDeplace(true);


        for (Direction direction : directions)
        {
            if (estEntrainDeCourir.get() && TypeCompetence.COURIR.getCompetence().estDebloquer())
                coefficientCourse = 2;

            joueur.setDirection(direction);
            joueur.seDeplace(coefficientCourse);
        }

    }


    public void ajoutDirection(Direction direction) {
        this.directions.add(direction);
    }

    /**
     * Récupère la touche relachée et l'enlève de la liste
     * @param direction
     */
    public void enleveDirection(Direction direction) {
        this.directions.remove(direction);
    }


}
