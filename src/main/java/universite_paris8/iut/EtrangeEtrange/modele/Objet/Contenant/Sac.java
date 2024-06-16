package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.ObjetConteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;

public class Sac extends ObjetConteneur<Objet>
{
    private static final int TAILLE = ConstanteObjet.TAILLE_SAC;
    private static final int STACK_MAX = ConstanteObjet.STACK_MAX_SAC;
    private static final int DURABILITEE = ConstanteObjet.DURABILITE_SAC;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_SAC;

    public Sac() {
        super(TAILLE);
    }

    @Override
    public int stackMax() { return STACK_MAX; }
    @Override
    public String getNom() {
        return "sac";
    }
    @Override
    public double durabilitee() {
        return DURABILITEE;
    }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }

}
