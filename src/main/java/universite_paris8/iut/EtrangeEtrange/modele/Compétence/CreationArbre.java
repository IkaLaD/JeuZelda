package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence.CompetenceGeneral.CompetenceStats.CompetenceUpPV;

import java.util.ArrayList;
import java.util.Arrays;

public class CreationArbre {
    public static Competences arbres() {
        Competences competences = new Competences();

        // Création des compétences
        CompetenceUpPV root = new CompetenceUpPV();
        CompetenceUpPV comp1 = new CompetenceUpPV();
        CompetenceUpPV comp2 = new CompetenceUpPV();
        CompetenceUpPV comp3 = new CompetenceUpPV();
        CompetenceUpPV comp4 = new CompetenceUpPV();
        CompetenceUpPV comp5 = new CompetenceUpPV();
        CompetenceUpPV comp6 = new CompetenceUpPV();
        CompetenceUpPV comp7 = new CompetenceUpPV();

        // Définir les relations parent-enfant
        ArrayList<Competence> rootParents = new ArrayList<>();
        ArrayList<Competence> rootEnfants = new ArrayList<>(Arrays.asList(comp1, comp2));

        ArrayList<Competence> comp1Parents = new ArrayList<>(Arrays.asList(root));
        ArrayList<Competence> comp1Enfants = new ArrayList<>(Arrays.asList(comp3, comp4));

        ArrayList<Competence> comp2Parents = new ArrayList<>(Arrays.asList(root));
        ArrayList<Competence> comp2Enfants = new ArrayList<>(Arrays.asList(comp5, comp6));

        ArrayList<Competence> comp3Parents = new ArrayList<>(Arrays.asList(comp1));
        ArrayList<Competence> comp3Enfants = new ArrayList<>(Arrays.asList(comp7));

        ArrayList<Competence> comp4Parents = new ArrayList<>(Arrays.asList(comp1));
        ArrayList<Competence> comp4Enfants = new ArrayList<>();

        ArrayList<Competence> comp5Parents = new ArrayList<>(Arrays.asList(comp2));
        ArrayList<Competence> comp5Enfants = new ArrayList<>();

        ArrayList<Competence> comp6Parents = new ArrayList<>(Arrays.asList(comp2));
        ArrayList<Competence> comp6Enfants = new ArrayList<>();

        ArrayList<Competence> comp7Parents = new ArrayList<>(Arrays.asList(comp3));
        ArrayList<Competence> comp7Enfants = new ArrayList<>();

        // Ajout des compétences dans l'arbre
        competences.ajoutCompetence(root, rootParents, rootEnfants);
        competences.ajoutCompetence(comp1, comp1Parents, comp1Enfants);
        competences.ajoutCompetence(comp2, comp2Parents, comp2Enfants);
        competences.ajoutCompetence(comp3, comp3Parents, comp3Enfants);
        competences.ajoutCompetence(comp4, comp4Parents, comp4Enfants);
        competences.ajoutCompetence(comp5, comp5Parents, comp5Enfants);
        competences.ajoutCompetence(comp6, comp6Parents, comp6Enfants);
        competences.ajoutCompetence(comp7, comp7Parents, comp7Enfants);

        // Définir la racine de l'arbre
        competences.setRoot(root);

        return competences;
    }
}
