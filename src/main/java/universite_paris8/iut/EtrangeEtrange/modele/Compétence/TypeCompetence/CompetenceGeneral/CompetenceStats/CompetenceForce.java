package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class CompetenceForce extends Competence
{
    private final int[] boostAttaque = new int[]{5,10,20};
    @Override
    public int niveauMax() {
        return 3;
    }

    @Override
    public void monterDeNiveau(Joueur joueur)
    {
        joueur.augmenterAttaqueMaximum(boostAttaque[niveauCompetence]);
    }
}
