package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceDebloquerAction;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;


public class CompetenceInvocation extends Competence
{
    @Override
    public int niveauMax() {
        return 1;
    }

    @Override
    public void monterDeNiveau(Joueur joueur)
    {
        if (niveauCompetence < niveauMax())
            niveauCompetence++;
    }


}
