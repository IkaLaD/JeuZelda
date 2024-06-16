package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;

public class Carquois extends ObjetConteneur<Fleche>
{
    private static final int STACK_MAX = ConstanteObjet.STACK_MAX_CARQUOIS;
    private static final int DURABILITEE = ConstanteObjet.DURABILITE_CARQUOIS;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_CARQUOIS;
    public Carquois() {
        super(1);
    }

    public Fleche retourneUneFleche()
    {
        return retourneObjet(0);
    }
    @Override
    public String getNom() {
        return "Carquois";
    }
    @Override
    public int stackMax() {
        return STACK_MAX;
    }
    @Override
    public double durabilitee() {
        return DURABILITEE;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }
    @Override
    public Fleche objetALemplacement(int emplacement) {
        return super.objetALemplacement(emplacement);
    }
}
