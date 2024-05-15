package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Conteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

import java.util.ArrayList;

public abstract class ObjetConteneur extends Objet implements Conteneur
{
    private Inventaire inv;

    public ObjetConteneur(int taille)
    {
        this.inv = new Inventaire(taille);
    }
    @Override
    public void ajoutItem(Objet objet)
    {
        this.inv.ajoutItem(objet);
    }

    @Override
    public void vider()
    {
        this.inv.vider();
    }

    @Override
    public boolean estPlein()
    {
        return this.inv.estPlein();
    }

    @Override
    public boolean estVide()
    {
        return this.inv.estVide();
    }

    @Override
    public int nombresObjets() {
        return this.inv.nombresObjets();
    }

    @Override
    public int getTailleMax() {
        return this.inv.getTailleMax();
    }

    @Override
    public ArrayList<Objet> retourneObjets(int emplacement) {
        return this.inv.retourneObjets(emplacement);
    }

    @Override
    public Objet retourneObjet(int emplacement) {
        return this.inv.retourneObjet(emplacement);
    }
}
