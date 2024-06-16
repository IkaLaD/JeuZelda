package universite_paris8.iut.EtrangeEtrange.modele.Parametres;


import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegeOrbe;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Support.SortilegeDeSoins;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class ConstanteObjet
{

    //-----------------------------------------------VARIABLE EPEE----------------------------------------------------//
    public static final double DEGAT_PHYSIQUE_EPEE = 20;
    public static final double DEGAT_SPECIAL_EPEE = 0;
    public static final double VITESSE_EPEE = 0.125;
    public static final Hitbox HITBOX_EPEE = new Hitbox(0.25,0.25);
    public static final int DURABILITE_EPEE = 10;
    public static final int PRIX_ACHAT_EPEE = 12;
    public static final long DELAIE_UTILISATION_EPEE = 50;
    public static final int STACK_MAX_EPEE = 1;


    //--------------------------------------------VARIABLE ARC--------------------------------------------------------//

    public static final int DURABILITE_ARC = 25;
    public static final int PRIX_ACHAT_ARC = 12;
    public static final long DELAIE_UTILISATION_ARC = 65;
    public static final int STACK_MAX_ARC = 1;

    //------------------------------------------------POTION----------------------------------------------------------//

    public static final int DURABILITE_POTION = 2;
    public static final int PRIX_ACHAT_POTION = 12;
    public static final int STACK_MAX_POTION = 1;
    public static final int RESTORATION = 20;

    //------------------------------------------------CARQUOIS--------------------------------------------------------//

    public static final int DURABILITE_CARQUOIS = -1;
    public static final int PRIX_ACHAT_CARQUOIS = 50;
    public static final int STACK_MAX_CARQUOIS = 1;

    //--------------------------------------------------SAC-----------------------------------------------------------//

    public static final int TAILLE_SAC = 15;
    public static final int DURABILITE_SAC = -1;
    public static final int PRIX_ACHAT_SAC = 50;
    public static final int STACK_MAX_SAC = 1;

    //------------------------------------------------FLECHE__________________________________________________________//

    public static final double DEGAT_PHYSIQUE_FLECHE = 20;
    public static final double DEGAT_SPECIAL_FLECHE = 0;
    public static final double VITESSE_FLECHE = 0.05;
    public static final Hitbox HITBOX_FLECHE = new Hitbox(0.25,0.25);
    public static final int DURABILITE_FLECHE = 10;
    public static final int PRIX_ACHAT_FLECHE = 12;
    public static final int STACK_MAX_FLECHE = 64;
    public static final int NOMBRE_UTLISATION_FLECHE = 1;

    //-----------------------------------------------ORBE-------------------------------------------------------------//

    public static final double DEGAT_PHYSIQUE_ORBE = 3;
    public static final double DEGAT_SPECIAL_ORBE = 7;
    public static final double VITESSE_ORBE = 0.05;
    public static final Hitbox HITBOX_ORBE = new Hitbox(0.2,0.2);
    public static final int PV_ORBE = 1;
    public static final int NOMBRE_UTLISATION_ORBE = 1;
    public static final int PRIX_ACHAT_ORBE = 15;
    public static final int STACK_MAX_ORBE = 64;
    public static final long DELAIE_CHERCHE_POSITION_ORBE = 200;


    //--------------------------------------------LIVRE MAGIQUE-------------------------------------------------------//

    public static Sortilege SORTILEGE1_LIVRE_MAGIQUE = new SortilegeOrbe();
    public static Sortilege SORTILEGE2_LIVRE_MAGIQUE = new SortilegePluitDeFleche();
    public static int SORT_MAXIMUM_LIVRE_MAGIQUE = 3;
    public static int STACK_MAX_LIVRE_MAGIQUE = 1;
    public static int PRIX_ACHAT_LIVRE_MAGIQUE = 120;
    public static int DURABILITE_LIVRE_MAGIQUE = -1;













}
