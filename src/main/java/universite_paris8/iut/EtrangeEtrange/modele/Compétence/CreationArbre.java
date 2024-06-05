package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral.CompetenceStats.CompetenceUpPV;

import java.util.ArrayList;
import java.util.Arrays;

public class CreationArbre {
    public static Competences arbres() {
        Competences competences = new Competences();

        // Création des compétences
        TypeCompetence root = TypeCompetence.COURIR;
        TypeCompetence comp1 = TypeCompetence.UP_ATTAQUE_SPECIAL;
        TypeCompetence comp2 = TypeCompetence.UP_ATTAQUE;
        TypeCompetence comp3 = TypeCompetence.UP_VITESSE;
        TypeCompetence comp4 = TypeCompetence.UP_PV;
        TypeCompetence comp5 = TypeCompetence.INVOQUER;
        TypeCompetence comp6 = TypeCompetence.UP_DEFENSE;
        TypeCompetence comp7 = TypeCompetence.UP_DEFENSE_SPECIAL;

        // Définir les relations parent-enfant
        ArrayList<TypeCompetence> rootParents = new ArrayList<>();
        ArrayList<TypeCompetence> rootEnfants = new ArrayList<>(Arrays.asList(comp1, comp2));

        ArrayList<TypeCompetence> comp1Parents = new ArrayList<>(Arrays.asList(root));
        ArrayList<TypeCompetence> comp1Enfants = new ArrayList<>(Arrays.asList(comp3, comp4));

        ArrayList<TypeCompetence> comp2Parents = new ArrayList<>(Arrays.asList(root));
        ArrayList<TypeCompetence> comp2Enfants = new ArrayList<>(Arrays.asList(comp5, comp6));

        ArrayList<TypeCompetence> comp3Parents = new ArrayList<>(Arrays.asList(comp1));
        ArrayList<TypeCompetence> comp3Enfants = new ArrayList<>(Arrays.asList(comp7));

        ArrayList<TypeCompetence> comp4Parents = new ArrayList<>(Arrays.asList(comp1));
        ArrayList<TypeCompetence> comp4Enfants = new ArrayList<>();

        ArrayList<TypeCompetence> comp5Parents = new ArrayList<>(Arrays.asList(comp2));
        ArrayList<TypeCompetence> comp5Enfants = new ArrayList<>();

        ArrayList<TypeCompetence> comp6Parents = new ArrayList<>(Arrays.asList(comp2));
        ArrayList<TypeCompetence> comp6Enfants = new ArrayList<>();

        ArrayList<TypeCompetence> comp7Parents = new ArrayList<>(Arrays.asList(comp3));
        ArrayList<TypeCompetence> comp7Enfants = new ArrayList<>();

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
