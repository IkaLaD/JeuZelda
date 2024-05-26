package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceDebloquerAction;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class CompetenceCourir extends Competence
{

    @Override
    public int niveauMax() {
        return 1;
    }

    @Override
    public void monterDeNiveau(Joueur joueur) {
        if (niveauCompetence == 0)
            joueur.peuCourir(true);
    }
}
