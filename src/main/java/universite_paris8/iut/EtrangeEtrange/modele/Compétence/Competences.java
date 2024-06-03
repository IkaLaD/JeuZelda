package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Competences
{

    private Competence root;
    private HashMap<Competence,ArrayList<Competence>> mapParent;
    private HashMap<Competence,ArrayList<Competence>> mapEnfant;







    public Competences()
    {
        this.mapParent = new HashMap<>();
    }



    public void debloquerCompetence(Competence competence,Joueur joueur)
    {
        if (!competence.estDebloquer() && parentDebloquer(competence)) {
            competence.debloquer();
            competence.monterDeNiveau(joueur);
        }
    }


    public void ajoutCompetence(Competence competence,ArrayList<Competence> parents,ArrayList<Competence> enfants)
    {
        if (!mapParent.containsKey(competence)) // verifier si competence not in parents
            mapParent.put(competence,parents);
    }




    private boolean parentDebloquer(Competence competence)
    {
        ArrayList<Competence> parentsCompetence = this.mapParent.get(competence);
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


    public Competence getRoot()
    {
        return this.root;
    }

    public ArrayList<Competence> getEnfants(Competence competence)
    {
        return this.mapEnfant.get(competence);
    }

    public ArrayList<Competence> getParents(Competence competence)
    {
        return this.mapParent.get(competence);
    }

}
