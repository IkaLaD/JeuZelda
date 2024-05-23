package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GestionCompetence
{
    private Competence competencePrincipal;
    private HashMap<Competence,ArrayList<Competence>> parent;
    public GestionCompetence(Competence competencePrincipal)
    {
        this.competencePrincipal = competencePrincipal;
    }
    
    public void debloquerCompetence(Competence competence)
    {

        if (!competence.estDebloquer() && parentDebloquer(competence))
            competence.debloquer();
    }


    private boolean parentDebloquer(Competence competence)
    {
        ArrayList<Competence> parentsCompetence = this.parent.get(competence);

        boolean parentDebloquer = false;

        if (parentsCompetence != null && !parentsCompetence.isEmpty() )
        {
            for (int i = 0;i<parentsCompetence.size() && !parentDebloquer;i++)
            {
                if (parentsCompetence.get(i).estDebloquer())
                    parentDebloquer = true;
            }
        }
        else
        {
            parentDebloquer = true;
        }


        return parentDebloquer;
    }





















}
