package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public class Fleche extends Projectile
{
    public static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_FLECHE;
    public static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_FLECHE;
    public static final double VITESSE = ConstanteObjet.VITESSE_FLECHE;
    public static final Hitbox HITBOX = ConstanteObjet.HITBOX_FLECHE;
    public static final int PV = ConstanteObjet.DURABILITE_FLECHE;
    public static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_FLECHE;
    public static final int STACK_MAX = ConstanteObjet.STACK_MAX_FLECHE;
    private static final int NOMBRE_UTILISATION = ConstanteObjet.NOMBRE_UTLISATION_FLECHE;
    private int nombreUtilisationRestant;

    public Fleche()
    {
        super(PV,VITESSE,HITBOX);
        this.nombreUtilisationRestant = NOMBRE_UTILISATION;

    }

    @Override
    public double degatPhysique() {
        return DEGAT_PHYSIQUE;
    }
    @Override
    public double degatSpecial() {
        return DEGAT_SPECIAL;
    }
    @Override
    public String getNom() {
        return "Fleche";
    }
    @Override
    public String typeActeur(){
        return "fleche";
    }

    @Override
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public int stackMax() {return STACK_MAX;}
    @Override
    public double durabilitee(){ return getPv(); }
    @Override
    public int prixAchat() { return PRIX_ACHAT; }

}
