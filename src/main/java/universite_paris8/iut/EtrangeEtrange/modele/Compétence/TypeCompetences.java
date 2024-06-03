package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceDebloquerAction.CompetenceCourir;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats.CompetenceUpPV;


public enum TypeCompetences
{
    COURIR(new CompetenceCourir()),
    INVOQUER_FAMILLIER(new CompetenceUpPV());
    
    private Competence competence;
    TypeCompetences (Competence competence) {
        this.competence = competence;
    }

    public Competence getCompetence()
    {
        return this.competence;
    }
}
