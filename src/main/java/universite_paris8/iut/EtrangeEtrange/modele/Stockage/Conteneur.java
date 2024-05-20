package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

import java.util.ArrayList;

public interface Conteneur<T extends Objet>
{
    boolean ajoutItem(T objet);

    void vider();
    boolean estPlein();
    boolean estVide();
    int nombresObjets();
    int getTailleMax();



    ArrayList<T> retourneObjets(int emplacement);
    T retourneObjet(int emplacement);
}
