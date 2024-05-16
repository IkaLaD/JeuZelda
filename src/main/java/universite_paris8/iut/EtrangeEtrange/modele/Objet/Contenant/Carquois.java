package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Conteneur;

public class Carquois extends ObjetConteneur implements Conteneur {
    public Carquois() {
        super(1);
    }

    @Override
    public String getNom() {
        return "Carquois";
    }

    @Override
    public int stackMax() {
        return 1;
    }

    @Override
    public void ajoutItem(Objet objet)
    {
        if (objet instanceof Fleche)
            inv.ajoutItem(objet);
    }

}
