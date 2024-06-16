package universite_paris8.iut.EtrangeEtrange.modele.Parametres;

import javafx.beans.property.StringProperty;

public class ConstantesPersonnages
{

    public static final double GUERRIER_PV = 80;
    public static final double GUERRIER_ATTAQUE = 70;
    public static final double GUERRIER_DEFENSE = 50;
    public static final double GUERRIER_ATTAQUE_SPECIAL = 10;
    public static final double GUERRIER_DEFENSE_SEPCIAL = 25;
    public static final double GUERRIER_VITESSE = 0.015;
    public static final double GUERRIER_HITBOX = 0.50;





    public static final double ARCHER_PV = 50;
    public static final double ARCHER_ATTAQUE = 50;
    public static final double ARCHER_DEFENSE = 40;
    public static final double ARCHER_ATTAQUE_SPECIAL = 20;
    public static final double ARCHER_DEFENSE_SEPCIAL = 40;
    public static final double ARCHER_VITESSE = 0.015;
    public static final double ARCHER_HITBOX_HAUTEUR = 0.35;
    public static final double ARCHER_HITBOX_LARGEUR = 0.50;


    public static final double MAGE_PV = 40;
    public static final double MAGE_ATTAQUE = 20;
    public static final double MAGE_DEFENSE = 30;
    public static final double MAGE_ATTAQUE_SPECIAL = 60;
    public static final double MAGE_DEFENSE_SEPCIAL = 45;
    public static final double MAGE_VITESSE = 0.06;
    public static final double MAGE_HITBOX = 0.50;



    public static final double NECROMANCIER_PV = 60;
    public static final double NECROMANCIER_ATTAQUE = 10;
    public static final double NECROMANCIER_DEFENSE = 30;
    public static final double NECROMANCIER_ATTAQUE_SPECIAL = 45;
    public static final double NECROMANCIER_DEFENSE_SEPCIAL = 50;
    public static final double NECROMANCIER_VITESSE = 0.04;
    public static final double NECROMANCIER_HITBOX = 0.50;




    public static final double pvPlusHaut()
    {
        return maximum(GUERRIER_PV,ARCHER_PV,MAGE_PV,NECROMANCIER_PV);
    }

    public static final double attaquePlusHaute() {
        return maximum(GUERRIER_ATTAQUE, ARCHER_ATTAQUE,MAGE_ATTAQUE,NECROMANCIER_ATTAQUE);
    }

    public static final double defensePlusHaute() {
        return maximum(GUERRIER_DEFENSE, ARCHER_DEFENSE,MAGE_DEFENSE,NECROMANCIER_DEFENSE);
    }

    public static final double attaqueSpecialPlusHaute() {
        return maximum(GUERRIER_ATTAQUE_SPECIAL, ARCHER_ATTAQUE_SPECIAL,MAGE_ATTAQUE_SPECIAL,NECROMANCIER_ATTAQUE_SPECIAL);
    }

    public static final double defenseSpecialPlusHaute() {
        return maximum(GUERRIER_DEFENSE_SEPCIAL, ARCHER_DEFENSE_SEPCIAL,MAGE_DEFENSE_SEPCIAL,NECROMANCIER_DEFENSE_SEPCIAL);
    }

    public static final double vitessePlusHaute() {
        return maximum(GUERRIER_VITESSE, ARCHER_VITESSE,MAGE_VITESSE,NECROMANCIER_VITESSE);
    }


    private static double maximum(double... stat) {
        double max = stat[0];

        for (int i = 1;i<stat.length;i++)
            if (stat[i] > max)
                max = stat[i];

        return max;
    }




































    public static final StringBuilder descriptionGuerrier()
    {
        return new StringBuilder("""
                Je suis né au fin fond d'un donjon obscur, élevé dans une forteresse oubliée où chaque jour était une lutte pour la survie. Mes maîtres étaient des guerriers impitoyables qui m'ont appris à manier toutes sortes d'armes et à tuer sans remords. J'ai été formé à ignorer la douleur, à repousser mes limites, et à survivre dans les environnements les plus hostiles. Mon enfance a été marquée par le sang et le sacrifice, et chaque cicatrice sur mon corps témoigne des épreuves surmontées.
                               
                Aujourd'hui, je suis devenu l'arme ultime, un guerrier façonné par les ténèbres pour éradiquer toute menace contre le monde. Je suis le gardien des anciens secrets et l'exécuteur silencieux de la justice implacable. Quiconque ose perturber l'équilibre du monde sera détruit sans pitié. Mon existence est dédiée à la protection du monde contre le chaos et la destruction. Mon nom est murmuré avec crainte et respect, car je suis l'ombre qui veille et la lame invisible qui frappe
                """);
    }



    public static final StringBuilder descriptionArcher()
    {
        return new StringBuilder("""
                Je suis né au cœur d'une forêt profonde, élevé parmi les chasseurs. Dès mon plus jeune âge, j'ai appris l'art du tir à l'arc avec des maîtres légendaires. J'ai passé mon enfance à perfectionner mon tir, à devenir invisible dans les ombres, et à traquer ma proie avec une précision mortelle.
                                
                Aujourd'hui, je suis un maître archer, gardien de la forêt et protecteur de l'équilibre naturel. Mon œil est aussi acéré que la pointe de mes flèches, et quiconque menace la paix de nos terres découvre la justice à travers mon arc. Je suis l'ombre silencieuse entre les arbres, l'œil vigilant qui veille sans cesse. Mon nom est chuchoté avec respect et crainte, car je suis né de la forêt et destiné à protéger avec une précision infaillible.
                """);
    }


    public static final StringBuilder descriptionMage()
    {
        return new StringBuilder("""
                Je suis né sous une lune mystique, élevé dans une tour ancienne où la magie imprègne chaque pierre. Dès mon plus jeune âge, j'ai été formé par des sages et des mages puissants. J'ai appris à lire les grimoires anciens, à maîtriser les incantations, et à canaliser les énergies mystiques.
                                
                Ma formation était rigoureuse. Chaque jour, j'étudiais les arcanes, pratiquais des sorts complexes, et méditais pour renforcer mon esprit. J'ai appris à manipuler les éléments, à guérir et à détruire, et à voir au-delà du voile de la réalité. Mes maîtres m'ont enseigné la sagesse et la discipline nécessaires pour manier un pouvoir immense sans en être consumé.
                                
                Aujourd'hui, je suis un maître mage, gardien des secrets anciens et protecteur de l'équilibre magique. Mon esprit est aussi acéré que la lame d'un épée, et ma volonté aussi inébranlable qu'une montagne. Quiconque menace l'harmonie du monde découvrira la puissance de mes sorts.
                                
                Je suis l'ombre silencieuse qui murmure les incantations, le gardien vigilant des forces invisibles. Mon nom est murmuré avec respect et crainte par ceux qui connaissent ma légende. Car je suis né sous la lune mystique, élevé par les arts magiques, et destiné à protéger avec une puissance insondable.
                """);
    }


    public static final StringBuilder descriptionNecromancier()
    {
        return new StringBuilder("""
                Je suis né au cœur d'une nuit sans lune, élevé dans les cryptes sombres d'une nécropole oubliée. Dès mon plus jeune âge, j'ai été formé par des maîtres nécromanciens, experts dans l'art de manipuler la vie et la mort. J'ai appris à lire les grimoires interdits,
                à invoquer les esprits des défunts, et à contrôler les forces obscures qui hantent les ombres.
                                
                Ma formation était impitoyable. Chaque jour, j'explorais les limites entre la vie et la mort, pratiquant des rituels macabres et apprenant les secrets des âmes perdues.\s
                J'ai découvert comment ranimer les morts, comment communiquer avec les esprits, et comment puiser dans l'énergie des ténèbres.
                 Mes maîtres m'ont enseigné la discipline et le pouvoir nécessaire pour maîtriser des forces terrifiantes.
                                
                Aujourd'hui, je suis un maître nécromancien, gardien des secrets interdits et seigneur des ombres.
                Mon esprit est aussi sombre que les cryptes où j'ai grandi.
                Quiconque ose troubler l'équilibre entre les vivants et les morts découvrira la terreur de mes pouvoirs.
                                
                Je suis l'ombre qui marche parmi les tombes, le murmure sinistre des âmes tourmentées.\s
                Mon nom est chuchoté avec peur et respect par ceux qui connaissent ma légende. Car je suis né de la nuit sans lune, élevé par les arts sombres, et destiné à régner sur les ténèbres avec une puissance funeste.
                """);
    }



}
