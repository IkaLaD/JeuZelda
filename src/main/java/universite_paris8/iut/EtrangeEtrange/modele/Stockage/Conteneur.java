package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;

public interface Conteneur
{
    void ajoutItem(Objet objet);

    void vider();
    boolean estPlein();
    boolean estVide();
    int nombresObjets();
    int getTailleMax();

    public <T extends Objet> T trouveObjet(Class<T> typeObjet);

    ArrayList<Objet> retourneObjets(int emplacement);
    Objet retourneObjet(int emplacement);
}
