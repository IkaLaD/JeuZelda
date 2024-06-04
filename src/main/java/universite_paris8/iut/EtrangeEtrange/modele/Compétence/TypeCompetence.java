package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral.CompetenceDebloquerAction.CompetenceCourir;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral.CompetenceStats.CompetenceUpPV;


public enum TypeCompetence
{
    COURIR(new CompetenceCourir()),
    INVOQUER(new CompetenceUpPV());
    
    private Competence competence;
    TypeCompetence(Competence competence) {
        this.competence = competence;
    }

    public Competence getCompetence()
    {
        return this.competence;
    }
}
