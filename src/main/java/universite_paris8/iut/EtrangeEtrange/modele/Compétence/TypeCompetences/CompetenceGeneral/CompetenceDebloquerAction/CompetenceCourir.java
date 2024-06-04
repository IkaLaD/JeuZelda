package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral.CompetenceDebloquerAction;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceActif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreCompetence.ParametreCourrir;

public class CompetenceCourir extends CompetenceActif
{

    @Override
    public int niveauMax() {
        return 1;
    }

    @Override
    public void monterDeNiveau(Joueur joueur) {

    }

    @Override
    public void utilise(ParametreAction param) {
        if (param instanceof ParametreCourrir)
        {
            Entite entite = param.getOrigineAction();
            entite.setDirection(((ParametreCourrir) param).getDirection());
            entite.seDeplace(2);
        }
    }
}
