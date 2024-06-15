package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral.CompetenceStats;


import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

public class CompetenceUpAttaque extends Competence
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
