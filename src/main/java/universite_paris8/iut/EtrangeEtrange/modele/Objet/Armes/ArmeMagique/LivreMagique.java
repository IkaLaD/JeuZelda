package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique.ParametreActionLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;

import java.util.ArrayList;

public abstract class LivreMagique extends Objet implements Utilisable
{
    private ArrayList<Sortilege> sortileges;



    public LivreMagique()
    {
        this.sortileges = new ArrayList<>();
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





    @Override
    public int stackMax() {
        return 1;
    }
}
