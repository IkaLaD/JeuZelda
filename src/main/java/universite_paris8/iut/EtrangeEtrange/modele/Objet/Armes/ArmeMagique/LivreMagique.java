package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.SortilegePluitDeFleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique.ParametreActionLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;

import java.util.ArrayList;

public  class LivreMagique extends Objet implements Utilisable
{
    private ArrayList<Sortilege> sortileges;
    private final int sortMaximum = 3;

    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
        this.sortileges.add(new SortilegePluitDeFleche());
    }

    @Override
    public String getNom() {
        return "Livre magique";
    }


    @Override
    public void utilise(ParametreActionObjet param)
    {
        if (param instanceof ParametreActionLivreMagique)
        {
            int numSort = ((ParametreActionLivreMagique) param).getNumSort();

            if (numSort <= sortileges.size())
                this.sortileges.get(numSort).action((EntiteOffensif) param.getOrigineAction());
        }
    }

    public void ajoutSortilege(Sortilege sortilege)
    {
        if (sortileges.size() +1 < sortMaximum)
            sortileges.add(sortilege);
    }

    @Override
    public int stackMax() {
        return 1;
    }

    public ArrayList<Sortilege> getSortileges() {
        return sortileges;
    }
}
