package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique.ParametreLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;


import java.util.ArrayList;

public  class LivreMagique implements Arme
{

    private static final Sortilege SORTILEGE1 = ConstanteObjet.SORTILEGE1_LIVRE_MAGIQUE;
    private static final int NOMBRE_SORT_MAXIMUM = ConstanteObjet.SORT_MAXIMUM_LIVRE_MAGIQUE;
    private static final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_LIVRE_MAGIQUE;
    private static final int STACK_MAX = ConstanteObjet.STACK_MAX_LIVRE_MAGIQUE;
    private static final int DURABILITEE = ConstanteObjet.DURABILITE_LIVRE_MAGIQUE;
    private ArrayList<Sortilege> sortileges;


    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(SORTILEGE1);
    }


    @Override
    public void utilise(Entite entite)
    {
        Sortilege sortilege = this.sortileges.get(0);
        sortilege.utilise(entite);
    }

    public void ajoutSortilege(Sortilege sortilege)
    {
        if (sortileges.size()+1 < NOMBRE_SORT_MAXIMUM)
            sortileges.add(sortilege);
    }

    public Sortilege getSortilege(int num)
    {
        Sortilege sortilege = null;

        if (num > 0 && num < sortileges.size()-1)
            sortilege = sortileges.get(num);

        return sortilege;
    }



    @Override
    public String getNom() {
        return "livremagique";
    }
    @Override
    public int stackMax() {
        return STACK_MAX;
    }
    @Override
    public double durabilitee() { return DURABILITEE; }
    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }


}
