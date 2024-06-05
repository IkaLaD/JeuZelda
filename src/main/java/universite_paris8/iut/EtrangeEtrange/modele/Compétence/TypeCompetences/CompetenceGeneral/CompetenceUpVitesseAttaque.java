package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class CompetenceUpVitesseAttaque extends Competence
{
    private final int[] deductionCooldownAttaque = new int[]{5,5,10,10};
    @Override
    public int niveauMax() {
        return 0;
    }

    @Override
    public void monterDeNiveau(Joueur joueur) {

    }
}
