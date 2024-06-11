package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceActif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import java.util.ArrayList;
import java.util.HashMap;

public class Competences
{

    private TypeCompetence root;
    private HashMap<TypeCompetence,ArrayList<TypeCompetence>> mapParent;
    private HashMap<TypeCompetence,ArrayList<TypeCompetence>> mapEnfant;


    public Competences()
    {
        this.mapParent = new HashMap<>();
        this.mapEnfant = new HashMap<>();
    }


    public boolean contientCompetence(TypeCompetence typeCompetence)
    {
        return mapEnfant.containsKey(typeCompetence) || mapParent.containsKey(typeCompetence);
    }













    public void debloquerCompetence(TypeCompetence competence,Joueur joueur)
    {
        if (!competence.getCompetence().estDebloquer() && parentDebloquer(competence)) {
            competence.getCompetence().debloquer();
            competence.getCompetence().monterDeNiveau(joueur);
        }
    }


    public void ajoutCompetence(TypeCompetence competence,ArrayList<TypeCompetence> parents,ArrayList<TypeCompetence> enfants)
    {
        if (!mapParent.containsKey(competence)) // verifier si competence not in parents
        {
            mapParent.put(competence, parents);
            mapEnfant.put(competence,enfants);
        }
    }




    private boolean parentDebloquer(TypeCompetence competence)
    {
        ArrayList<TypeCompetence> parentsCompetence = this.mapParent.get(competence);
        boolean parentDebloquer = false;

        if (parentsCompetence != null && !parentsCompetence.isEmpty() )
        {
            for (int i = 0;i<parentsCompetence.size() && !parentDebloquer;i++)
            {
                if (parentsCompetence.get(i).getCompetence().estDebloquer())
                    parentDebloquer = true;
            }
        }
        else
        {
            parentDebloquer = true;
        }

        return parentDebloquer;
    }

    public void setRoot(TypeCompetence competence)
    {
        this.root = competence;
    }

    public TypeCompetence getRoot()
    {
        return this.root;
    }

    public ArrayList<TypeCompetence> getEnfants(TypeCompetence competence)
    {
        return this.mapEnfant.get(competence);
    }

    public ArrayList<TypeCompetence> getParents(TypeCompetence competence)
    {
        return this.mapParent.get(competence);
    }

}
