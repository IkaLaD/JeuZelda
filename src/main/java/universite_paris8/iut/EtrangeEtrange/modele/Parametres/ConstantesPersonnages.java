package universite_paris8.iut.EtrangeEtrange.modele.Parametres;

public class ConstantesPersonnages
{

    public static final double GUERRIER_PV = 80;
    public static final double GUERRIER_ATTAQUE = 70;
    public static final double GUERRIER_DEFENSE = 50;
    public static final double GUERRIER_ATTAQUE_SPECIAL = 10;
    public static final double GUERRIER_DEFENSE_SEPCIAL = 25;
    public static final double GUERRIER_VITESSE = 0.05;
    public static final double GUERRIER_HITBOX = 0.50;





    public static final double ARCHER_PV = 50;
    public static final double ARCHER_ATTAQUE = 50;
    public static final double ARCHER_DEFENSE = 40;
    public static final double ARCHER_ATTAQUE_SPECIAL = 20;
    public static final double ARCHER_DEFENSE_SEPCIAL = 40;
    public static final double ARCHER_VITESSE = 0.08;
    public static final double ARCHER_HITBOX = 0.50;


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

}
