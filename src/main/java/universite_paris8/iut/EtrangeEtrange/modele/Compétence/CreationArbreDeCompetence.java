package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats.CompetenceForce;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats.CompetenceUpDefense;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats.CompetenceUpPV;

import java.util.ArrayList;

public class CreationArbreDeCompetence
{
    private  GestionCompetence gestionCompetence;


    public GestionCompetence creationArbreGuerrier()
    {
        Competence racine = new CompetenceForce();
        ArrayList<Competence> enfantRacine = new ArrayList<>();


        Competence competencePv = new CompetenceUpPV();
        Competence competenceDef = new CompetenceUpDefense();


        this.gestionCompetence.ajoutCompetence(racine,new ArrayList<>(),enfantRacine);

        enfantRacine.add(competencePv);
        enfantRacine.add(competenceDef);



        return this.gestionCompetence;

    }


}
