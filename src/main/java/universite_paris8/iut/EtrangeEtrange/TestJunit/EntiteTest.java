package universite_paris8.iut.EtrangeEtrange.TestJunit;


import org.junit.Test;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Guerrier;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import static org.junit.Assert.assertEquals;


public class EntiteTest
{

    private Monde monde = new Monde("src/main/resources/universite_paris8/iut/EtrangeEtrange/TiledMap/", "maptest", Monde.getSizeMondeHauteur(), Monde.getSizeMondeLargeur());


    @Test
    public void testDeplacement()
    {
        Entite entite = new Guerrier(monde,10,10, Direction.GAUCHE);

        entite.seDeplace(1);

        double newXposition = 10 - (1 * entite.getStatsVitesse().getVitesse());
        assertEquals(newXposition, entite.getPosition().getX());

        entite.setPosition(10,10);
        entite.setDirection(Direction.DROITE);
        entite.seDeplace(1);
        double newXposition2 = 10 + 1 * (entite.getStatsVitesse().getVitesse());
        assertEquals(newXposition2, entite.getPosition().getX());



        entite.setPosition(10,10);
        entite.setDirection(Direction.BAS);
        entite.seDeplace(1);
        double newYposition = 10 + 1 * (entite.getStatsVitesse().getVitesse());
        assertEquals(newYposition, entite.getPosition().getY());

        entite.setPosition(10,10);
        entite.setDirection(Direction.HAUT);
        entite.seDeplace(1);
        double newYposition2 = 10 - 1 * (entite.getStatsVitesse().getVitesse());
        assertEquals(newYposition2, entite.getPosition().getY());
    }








}
