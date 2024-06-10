package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant;

import javafx.beans.property.IntegerProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Conteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.Inventaire;

import java.util.ArrayList;

public abstract class ObjetConteneur<T extends Objet> implements Objet, Conteneur<T>
{
    private Inventaire<T> inv;

    public ObjetConteneur(int taille)
    {
        this.inv = new Inventaire<>(taille);
    }
    @Override
    public boolean ajoutItem(T objet)
    {
       return this.inv.ajoutItem(objet);
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
    public IntegerProperty getTailleMaxProperty(){
        return inv.getTailleMaxProperty();
    }

    @Override
    public T objetALemplacement(int emplacement){
        return inv.objetALemplacement(emplacement);
    }
    @Override
    public ArrayList<T> enleverObjet(int emplacement) {
        return this.inv.enleverObjet(emplacement);
    }

    @Override
    public T retourneObjet(int emplacement) {
        return this.inv.retourneObjet(emplacement);
    }
    public Inventaire<T> getInv(){
        return this.inv;
    }

    public String toString(){
        return inv.toString();
    }

    public void echangerEmplacement(Joueur joueur, int caseVerouille, int caseSurvole) {
        int tailleInventaire = joueur.getSac().getTailleMax();
        Objet o1;
        Objet o2;

        if(caseSurvole==tailleInventaire)
            o1 = joueur.retournerObjetMainDroite();
        else if (caseSurvole==tailleInventaire+1)
            o1 = joueur.retournerObjetMainGauche();
        else
            o1 = inv.retourneObjet(caseSurvole);
        if(caseVerouille==tailleInventaire)
            o2 = joueur.retournerObjetMainDroite();
        else if (caseVerouille==tailleInventaire+1)
            o2 = joueur.retournerObjetMainGauche();
        else
            o2 = inv.retourneObjet(caseVerouille);

        if(o2!=null) {
            if (caseSurvole == tailleInventaire)
                joueur.setObjetMainDroite(o2);
            else if (caseSurvole == tailleInventaire + 1)
                joueur.setObjetMainGauche(o2);
            else
                inv.getEmplacement(caseSurvole).ajoutObjet((T) o2);
        }
        if(o1!=null){
            if(caseVerouille==tailleInventaire)
                joueur.setObjetMainDroite(o1);
            else if(caseVerouille==tailleInventaire+1)
                joueur.setObjetMainGauche(o1);
            else
                inv.getEmplacement(caseVerouille).ajoutObjet((T)o1);
        }

    }
}
