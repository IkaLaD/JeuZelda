package universite_paris8.iut.EtrangeEtrange.modele.Stockage;

import javafx.beans.property.IntegerProperty;
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


    IntegerProperty getTailleMaxProperty();

    T objetALemplacement(int emplacement);

    ArrayList<T> enleverObjet(int emplacement);
    T retourneObjet(int emplacement);
}
