package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreationArbre {
    public static Competences arbres() {
        Competences competences = new Competences();

        // Création des compétences
        TypeCompetence root = TypeCompetence.COURIR;
        TypeCompetence attaqueSpecial = TypeCompetence.UP_ATTAQUE_SPECIAL;
        TypeCompetence attaque = TypeCompetence.UP_ATTAQUE;
        TypeCompetence vitesse = TypeCompetence.UP_PV2;
        TypeCompetence pv = TypeCompetence.UP_PV;
        TypeCompetence invoquer = TypeCompetence.INVOQUER;
        TypeCompetence defense = TypeCompetence.UP_DEFENSE;
        TypeCompetence defenseSpecial = TypeCompetence.UP_DEFENSE_SPECIAL;

        // Définir les relations parent-enfant
        ArrayList<TypeCompetence> rootParents = new ArrayList<>();
        ArrayList<TypeCompetence> rootEnfants = new ArrayList<>(List.of(attaqueSpecial));

        ArrayList<TypeCompetence> attaqueSpecialParents = new ArrayList<>(Arrays.asList(root));
        ArrayList<TypeCompetence> attaqueSpecial1Enfants = new ArrayList<>(Arrays.asList(attaque, vitesse));

        ArrayList<TypeCompetence> attaqueParents = new ArrayList<>(Arrays.asList(attaqueSpecial));
        ArrayList<TypeCompetence> attaqueEnfants = new ArrayList<>();

        ArrayList<TypeCompetence> vitesseParents = new ArrayList<>(Arrays.asList(attaqueSpecial));
        ArrayList<TypeCompetence> vitesseEnfants = new ArrayList<>(Arrays.asList(defense,pv));

        ArrayList<TypeCompetence> pvParents = new ArrayList<>(Arrays.asList(vitesse));
        ArrayList<TypeCompetence> pvEnfants = new ArrayList<>();

        ArrayList<TypeCompetence> defenseParents = new ArrayList<>(Arrays.asList(vitesse));
        ArrayList<TypeCompetence> defenseEnfants = new ArrayList<>(Arrays.asList(defenseSpecial));

        ArrayList<TypeCompetence> defenseSpecialParents = new ArrayList<>(Arrays.asList(defenseSpecial));
        ArrayList<TypeCompetence> defenseSpecialEnfants = new ArrayList<>(Arrays.asList(invoquer));



        // Ajout des compétences dans l'arbre
        competences.ajoutCompetence(root, rootParents, rootEnfants);
        competences.ajoutCompetence(attaqueSpecial, attaqueSpecialParents, attaqueSpecial1Enfants);
        competences.ajoutCompetence(attaque, attaqueParents, attaqueEnfants);
        competences.ajoutCompetence(vitesse, vitesseParents, vitesseEnfants);
        competences.ajoutCompetence(defense, defenseParents, defenseEnfants);
        competences.ajoutCompetence(defenseSpecial, defenseSpecialParents, defenseSpecialEnfants);
        competences.ajoutCompetence(pv, pvParents, pvEnfants);
        competences.ajoutCompetence(invoquer, new ArrayList<>(List.of(defenseSpecial)),new ArrayList<>() );

        // Définir la racine de l'arbre
        competences.setRoot(root);

        return competences;
    }
}
