package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceDebloquerAction.CompetenceCourir;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceDebloquerAction.CompetenceInvocation;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceStats.*;


public enum TypeCompetence
{
    UP_PV(new CompetenceUpPV()),
    UP_PV2(new CompetenceUpPV()),
    UP_PV3(new CompetenceUpPV()),


    UP_ATTAQUE(new CompetenceUpAttaque()),
    UP_DEFENSE(new CompetenceUpDefense()),
    UP_ATTAQUE_SPECIAL(new CompetenceUpAttaqueSpecial()),
    UP_DEFENSE_SPECIAL(new CompetenceUpDefenseSpecial()),
    COURIR(new CompetenceCourir()),
    INVOQUER(new CompetenceInvocation());
    
    private Competence competence;
    TypeCompetence(Competence competence) {
        this.competence = competence;
    }

    public Competence getCompetence()
    {
        return this.competence;
    }


}
