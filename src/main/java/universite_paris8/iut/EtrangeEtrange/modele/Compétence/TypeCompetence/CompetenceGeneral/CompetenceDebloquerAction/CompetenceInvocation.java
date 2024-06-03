package universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceDebloquerAction;


import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceActif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreCompetence.ParametreInvocation;

public class CompetenceInvocation extends CompetenceActif
{
    private boolean[] niveau;

    public CompetenceInvocation()
    {
        this.niveau = new boolean[niveauMax()];
    }

    @Override
    public int niveauMax() {
        return 5;
    }

    @Override
    public void monterDeNiveau(Joueur joueur)
    {
        niveau[niveauCompetence++] = true;
    }

    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreInvocation)
        {
            Entite entite = param.getOrigineAction();


        }
    }
}
